# Network Security

---------

**이 repo는 네트워크 보안 공부를 진행하면서 알게된 점 및 Java로 암호를 구현하고 리팩토링 하는 과정을 담았습니다.**



1. **Caesar 암호**

   : 시저 암호의 평행이동 하는 암호이다. 알파벳의 경우 26개 이므로 키 공간의 크기가 26이다. 

   ```java
   	for(int i=0; i<Plane.length(); i++) {	
   		int num = -1; //연산중 공백처리를 위한 초기값 설정 
   		
   		for(int j=0; j<word.length(); j++) {	
   			if(Plane.charAt(i) == word.charAt(j)) {	//만일 두 문자가 같다면
   				num = (j+Key) % word.length();		
   				break;		
   			}
   		}
   		
   		if (num == -1)		
   			result += Plane.charAt(i);	
   		else
   			result += word.charAt(num);	
   	}
   ```

   리팩토링 후 아래와 같이 변경

   ```java
   for(char s:plain_Arry) {
       if(s==' '|| s =='.'){
           result +=s;
       } else{
           int num = (word.indexOf(s) + Key) % word.length();
           result += word.charAt(num);
       }
   }
   ```

2. **Transition 암호**

   : 전치 암호로 자리를 바꾸어 주는 암호이다. 

3. **P-Box** 

   :전치 암호로 DES 과정에서 사용한다. 

   ```java
   for(int i=0; i<P.length(); i++) {
   			result += P.charAt(index[i]-1);	
   		}
   ```

   리팩토링 후 아래와 같이 변경

   ```java
   for(int a:index){
       result +=P.charAt(a-1);
   }
   ```

   foreach문을 이용해 주었다. key = index 배열인데 {2, 6, 3, 1, 4, 8, 5, 7}이고 P인 평문이 "ABCDEFGH"이라면 2번째의 문자가 처음으로 그 다음 6번째 문자가 오는 방식의 전치암호이다.  최종적으로 index를 돌면서 해당 위치에 있는 Plain text를 붙여 주었다. 

