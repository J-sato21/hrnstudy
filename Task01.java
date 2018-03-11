/**********************************************************
*作成日時：2018/03/11
*作成者：佐藤惇平
*システム概要：各国の時差を求めたい
*作成目的：課題
*リビジョン：1.0
**********************************************************/
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
	 
	public class Task01 {
	    public static void main(String args[] ) throws Exception {
	        Scanner sc = new Scanner(System.in);

	        /*都市の総数を表す整数を受け取る*/
	        int number_of_cities = 0;
	        /*入力値のチェック*/    
	        try {
	        	number_of_cities = sc.nextInt();
	        } catch (InputMismatchException e) {
	        	System.out.println("数値を入力してください");
	        	return;
	    		}
	        if (number_of_cities < 1 || number_of_cities > 100) {
	        	System.out.println("都市の数は1～100で入力してください");
	        	return;
	        	}
	        
	        /*入力された各都市の情報*/
	        String city_info_name[] = new String[number_of_cities];  //都市名
	        int city_info_time[] = new int[number_of_cities]; //時間
	        
	        /*都市の総数だけ繰り返し配列に格納*/
	        for(int i = 0; i < number_of_cities; i++) {
	        	city_info_name[i] = sc.next();
	        	
	        	/*入力値のチェック
	        	 * 都市名
	        	 * 入力制限(英字小文字のみ,1～20文字以内)
	        	 * */
	        	if (city_info_name[i].length() < 0 || city_info_name[i].length() > 20) {
		        	System.out.println("都市名は1～20文字以内で入力してください");
		        	return;
		        	} 
	        	for (int j = 0; j < city_info_name[i].length(); j++) {
	        		if (Character.isUpperCase(city_info_name[i].charAt(j))) {
		        		System.out.println("都市名は英字小文字で入力してください");
			        	return;
	        		}
	        	}
	        	
	        	/*入力値のチェック
	        	 * 標準時間との誤差
	        	 * 入力制限(数値チェック,-12～14以内)
	        	 * */
	        	try {
	        	city_info_time[i] = sc.nextInt();
	        } catch (InputMismatchException e) {
	        	System.out.println("標準時間との誤差は数値を入力してください");
	        	return;
	        }
	        	if (city_info_time[i] < -12 || city_info_time[i] > 14) {
		        	System.out.println("標準時間との誤差は-12～14で入力してください");
		        	return;
		        	} 
	        }
	        
	        /*投稿者の情報*/
	        String user_info_city = null; //都市名
	        String user_info_time = null; //時間
	        user_info_city = sc.next();
	        user_info_time = sc.next();
        	
	        /*投稿時間のチェック
	         * 日付のフォーマットチェック
	         * */
	        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
	        Matcher m = p.matcher(user_info_time);
	        if(!m.find() ) {
	        	System.out.println("投稿時間は00:00～23:59の間で入力して下さい");
	        	return;
	        }
	        
	        /*標準時間との計算用に投稿時間を格納*/
	        int user_time_hh = 0; //時
	        int user_time_mm = 0; //分
	        user_time_hh = Integer.parseInt(user_info_time.substring(0,2));
	        user_time_mm = Integer.parseInt(user_info_time.substring(3,5));
	        
        	/*投稿者の都市名の同値の有無のチェック*/
	        int city_check = 0;
        	for (int n = 0; n < number_of_cities; n++) {
        		if (city_info_name[n].equals(user_info_city)) {
        			user_time_hh = user_time_hh - city_info_time[n]; //投稿者の時刻と標準時間を計算
        			city_check++;
        			break;
        		}
        	}
        	if (city_check == 0) {
        		System.out.println("投稿者の都市名は各ユーザの都市名に存在するものにしてください");
        		return;
        	}
	        
	        /*改行処理*/
	        System.out.println();
	        
	        /*日時の計算用のCalendarクラスとフォーマットの宣言*/
	        Calendar cal1 = Calendar.getInstance();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

	        for (int i = 0; i < number_of_cities; i++) {
	        	/*日時を計算して出力*/
	        	cal1.set( Calendar.HOUR_OF_DAY, user_time_hh );
	        	cal1.set( Calendar.MINUTE, user_time_mm );
		        cal1.add(Calendar.HOUR, city_info_time[i]);
	            System.out.println(city_info_name[i] + " " + sdf1.format(cal1.getTime()));   
	        }
	    }
	}