package day4.mybatis.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter				
@AllArgsConstructor				// 커스텀생성자 : 컬럼명과 변수명 일치 안해도 된다.
								// 변수 선언 순서와 컬럼순서는 일치해야 합니다.
@NoArgsConstructor	
@EqualsAndHashCode
public class CustomerDto {

	public String custom_id;
    public String name;
    public String email;
    public int age;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    public Date reg_date;
  
    
    @Override
    public String toString() {
        return String.format("\n%6s %15s %40s\t\t %3d \t\t %tD", 
        		custom_id,name,email,age,reg_date.getTime());
    }
    
}
