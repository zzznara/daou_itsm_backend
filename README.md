# 모바일 back-end
## 개발 PC 환경설정
### profile 설정(Active profiles)
* local : 로컬 개발 환경

### VM options(VM arguments)
`-Duser.language=ko -Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms1024m -Xmx1024m -XX:NewSize=512m -XX:MaxNewSize=512m -XX:+DisableExplicitGC -Djava.net.preferIPv4Stack=true -Dspring.profiles.active=local`

### application-loca.yml 파일
* 업로드 위치
<pre>
server:  
  servlet:  
    multipart:  
      location: PC 폴더 경로 (예: C:\\upload)
</pre>
<pre>
file:  
  upload:  
    base-path-upload: location: PC 폴더 경로 (예: C:\\upload)
</pre>
* 로그 위치
<pre>
logging:  
  file:  
    path: PC 폴더 경로 (예: C:\\log)
</pre>