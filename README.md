# JAVA_project
socket 통신을 이용한 캐치마인드 게임

##1.실행방법 
CatchMindServer 폴더 내에 CatchMindServer.java 파일을 실행하여 포트를 열어준다
CatchMindClient 폴더 내에 CatchMindClientMain.java 파일을 실행하여 클라이언트로 접속한다

##2.동작원리
swing 이용해 gui 제작
socket 통신을 통해 포트를 열고 클라이언트 참여(chat,view 공유)
클라이언트를 vector[]에 저장
defalut로 AuthorityOfDrawing = false 상태, 그림판 이용 불가능, 채팅가능 
AuthorityOfDrawing = true 인 클라이언트는 그림판 사용 가능, getPoint()를 통해 마우스의 x y축을 읽어 그림판 공유 
answer(정답)과 클라이언트의 채팅메세지가 일치하면 다음 사람에게 AuthorityOfDrawing = true



