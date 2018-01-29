/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.utlis;

public class lbTest {
	static StringBuffer dataBuffer =  new StringBuffer();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//onDataReceived("16100228BE5B1E475E957C1E011D0D36F88048369E6A85CDB35B3640CA7F2A9021003FF161002719150918A43128C503014B4087502980EA51401774FBD6B4708C4988F1D7A571003FF");
		/*onDataReceived("1610");
		onDataReceived("0230001D01028A5A005A05002D2D03168C50021140E2801D4B400B4C66C9C0A007A2E07BD3C5003A9680014EA005A2818B40A042D2D03168A00514B40828A005");
		onDataReceived("A281852D001498CD00348C1E29C1B3400ECD14008CD81518F9CE4D004A0629680168A00296800A2800A2800A2800A2800A4A0028A004CD37A9A005C5274A0051");
		onDataReceived("45002D2D002D28A621452D218A2826803FFFD2CEA29902D2D000052D002D2D002D2D030A28016908A004069D9A006972C7029C8BEB408929C2818B45003852D0");
		onDataReceived("014B4000A5A062D28A005A280168A005A280168A04145218B45300A691400A0D296C0CD0047CBB7B548A30281010EA2818B450014B4005140051400514005250");
		onDataReceived("0145300A4A40349C9A5028016908CD00028A005A5A00514EA00514B40051D4D007FFD91003FF1610022F0000001003FF");*/
		
		onDataReceived("161002");
		onDataReceived("4B9A062E6826801A68C500281411400C22A9DD302E00ED401242BF28A9C74A001003FF161002300000024C514008464557718A008CD46C2980DA4A0028A00FFF");
		onDataReceived("D2E6A90A67A5004A96DF2E5CF6A824014E050035724D48AB401308D80C9181576CE6D842FAF140D1A2BCE2A75E95251010DC5CAA82A393596FC317CE282A1A32DDA457372404462BEA7A56ADBE89231DD2BE38E82848D6A5449684977A62A212AC41ACE68FCB6C13935563979AE4AAD919A09A00434A10101A044CB80303A53B340C5CD2134008690D00368CD0019A09E2802ADC4B8E0567C92859377A500569A76931939C5539E424019E69B93624AC08C02FCC79E94D63C7CA290CAC4EE9B27B54831B46680056C74A4C12E18F38A0079C719ED4D3205E94808A472D4090ECDBED8A603704D285F5A00518147269006297205301A5E985A90084D25300A7A1C1A00B713702A60690870A75030A5A005C518A0028A00AF3CC11700F35514195F27A5005C45DA29F4009498A0043D2A171D68022239A8D8530194940051401FFD3E6CF069CA68025219D71BB03D2AA3A60D003A35E46455C891157919340092B6720702A38A4D8E0D008D386F508E7834B35E9DB84A9B16526973C9EB525B4B109819B1B4503B9D1D96A31CA42C0BC0EF56E6BF78D7E55E6A8CDF99972DECF70E4160AB503293CE49A0048CF51525021C053871400E0697340C5CD2668013341340084D25201334C91F6A9A6233E77279ACF9A419A06576900E40A810991CFBD004CCAA3A9A8E4942A101005001003FF");
	}

	public static void onDataReceived(String data){

		try {
			String [] dataAry = data.split("FF16");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						String abcString = dataBuffer.toString()+dataAry[j];
						if(abcString.endsWith("1003")){
							dataBuffer.append(dataAry[j]+"FF");
							System.out.println("================"+dataBuffer.toString());
							dataBuffer  = new StringBuffer();
						}else{
							dataBuffer.append(dataAry[j]);
						}
					}else if(j == (num-1)){
						if(data.endsWith("FF16")){
							dataBuffer.append("16"+dataAry[j]+"FF");
							System.out.println("================"+dataBuffer.toString());
							dataBuffer  = new StringBuffer();

							dataBuffer.append("FF");
						}else{
							if(dataAry[j].endsWith("FF")){
								dataBuffer.append("16"+dataAry[j]);
								if(dataBuffer.toString().startsWith("161002") && dataBuffer.toString().endsWith("1003FF")){
									System.out.println("================"+dataBuffer.toString());
									dataBuffer  = new StringBuffer();
								}
							}else{
								dataBuffer.append("16"+dataAry[j]);
							}
						}
					}else{
						dataBuffer.append("16"+dataAry[j]+"FF");
						System.out.println("================"+dataBuffer.toString());
						dataBuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("FF16")){
					if(data.equals("FF16")){
						dataBuffer.append("FF");
					}else{
						dataBuffer.append(dataAry[0]+"FF");
					}
					System.out.println("================"+dataBuffer.toString());
					dataBuffer  = new StringBuffer();
					dataBuffer.append("16");
				}else{
					if(data.endsWith("FF")){
						dataBuffer.append(dataAry[0]);
						if(dataBuffer.toString().startsWith("161002") && dataBuffer.toString().endsWith("1003FF")){
							System.out.println("================"+dataBuffer.toString());
							dataBuffer  = new StringBuffer();
						}
					}else{
						dataBuffer.append(dataAry[0]);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
