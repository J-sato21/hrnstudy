/**********************************************************
*�쐬�����F2018/03/11
*�쐬�ҁF�����Օ�
*�V�X�e���T�v�F�e���̎��������߂���
*�쐬�ړI�F�ۑ�
*���r�W�����F1.0
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

	        /*�s�s�̑�����\���������󂯎��*/
	        int number_of_cities = 0;
	        /*���͒l�̃`�F�b�N*/    
	        try {
	        	number_of_cities = sc.nextInt();
	        } catch (InputMismatchException e) {
	        	System.out.println("���l����͂��Ă�������");
	        	return;
	    		}
	        if (number_of_cities < 1 || number_of_cities > 100) {
	        	System.out.println("�s�s�̐���1�`100�œ��͂��Ă�������");
	        	return;
	        	}
	        
	        /*���͂��ꂽ�e�s�s�̏��*/
	        String city_info_name[] = new String[number_of_cities];  //�s�s��
	        int city_info_time[] = new int[number_of_cities]; //����
	        
	        /*�s�s�̑��������J��Ԃ��z��Ɋi�[*/
	        for(int i = 0; i < number_of_cities; i++) {
	        	city_info_name[i] = sc.next();
	        	
	        	/*���͒l�̃`�F�b�N
	        	 * �s�s��
	        	 * ���͐���(�p���������̂�,1�`20�����ȓ�)
	        	 * */
	        	if (city_info_name[i].length() < 0 || city_info_name[i].length() > 20) {
		        	System.out.println("�s�s����1�`20�����ȓ��œ��͂��Ă�������");
		        	return;
		        	} 
	        	for (int j = 0; j < city_info_name[i].length(); j++) {
	        		if (Character.isUpperCase(city_info_name[i].charAt(j))) {
		        		System.out.println("�s�s���͉p���������œ��͂��Ă�������");
			        	return;
	        		}
	        	}
	        	
	        	/*���͒l�̃`�F�b�N
	        	 * �W�����ԂƂ̌덷
	        	 * ���͐���(���l�`�F�b�N,-12�`14�ȓ�)
	        	 * */
	        	try {
	        	city_info_time[i] = sc.nextInt();
	        } catch (InputMismatchException e) {
	        	System.out.println("�W�����ԂƂ̌덷�͐��l����͂��Ă�������");
	        	return;
	        }
	        	if (city_info_time[i] < -12 || city_info_time[i] > 14) {
		        	System.out.println("�W�����ԂƂ̌덷��-12�`14�œ��͂��Ă�������");
		        	return;
		        	} 
	        }
	        
	        /*���e�҂̏��*/
	        String user_info_city = null; //�s�s��
	        String user_info_time = null; //����
	        user_info_city = sc.next();
	        user_info_time = sc.next();
        	
	        /*���e���Ԃ̃`�F�b�N
	         * ���t�̃t�H�[�}�b�g�`�F�b�N
	         * */
	        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
	        Matcher m = p.matcher(user_info_time);
	        if(!m.find() ) {
	        	System.out.println("���e���Ԃ�00:00�`23:59�̊Ԃœ��͂��ĉ�����");
	        	return;
	        }
	        
	        /*�W�����ԂƂ̌v�Z�p�ɓ��e���Ԃ��i�[*/
	        int user_time_hh = 0; //��
	        int user_time_mm = 0; //��
	        user_time_hh = Integer.parseInt(user_info_time.substring(0,2));
	        user_time_mm = Integer.parseInt(user_info_time.substring(3,5));
	        
        	/*���e�҂̓s�s���̓��l�̗L���̃`�F�b�N*/
	        int city_check = 0;
        	for (int n = 0; n < number_of_cities; n++) {
        		if (city_info_name[n].equals(user_info_city)) {
        			user_time_hh = user_time_hh - city_info_time[n]; //���e�҂̎����ƕW�����Ԃ��v�Z
        			city_check++;
        			break;
        		}
        	}
        	if (city_check == 0) {
        		System.out.println("���e�҂̓s�s���͊e���[�U�̓s�s���ɑ��݂�����̂ɂ��Ă�������");
        		return;
        	}
	        
	        /*���s����*/
	        System.out.println();
	        
	        /*�����̌v�Z�p��Calendar�N���X�ƃt�H�[�}�b�g�̐錾*/
	        Calendar cal1 = Calendar.getInstance();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

	        for (int i = 0; i < number_of_cities; i++) {
	        	/*�������v�Z���ďo��*/
	        	cal1.set( Calendar.HOUR_OF_DAY, user_time_hh );
	        	cal1.set( Calendar.MINUTE, user_time_mm );
		        cal1.add(Calendar.HOUR, city_info_time[i]);
	            System.out.println(city_info_name[i] + " " + sdf1.format(cal1.getTime()));   
	        }
	    }
	}