<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"
		/>
		<style>
			body {
				width: 100vw;
				height: 100vh;
				margin: 0 auto;
				font-family: "Pretendard", "NotoSansKR", "Malgun Gothic";
				background:  #1e54a2;
				background-size: cover;
				background-position: center;
			}
			.title{
				text-align: center;
				color: #fff;
			}
			.glassBox{
				width: 100%;
				margin-top: 24px;
				padding: 32px 0 24px;
				border-radius: 28px;
				background: rgba(0, 0, 0, 0.20);
				backdrop-filter: blur(5px);

			}
			.menu{
				display: flex;
				flex-wrap: wrap;
				justify-content: center;
			}
			.menu>div{width: 100%;}
			.iconDiv{
				margin: 0 auto;
				height: 60px;
				position: relative;
				overflow: hidden;
				width: 200px;
				display: flex;
				justify-content: center;
				align-items: center;
				background-color: #000000;
				border: 2px solid #a6a6a6;
				border-radius: 5px;
				box-sizing: border-box;
				transition-duration: 0.3s;
			}

			a:link{text-decoration: none;}
			.iconDiv>a{
				height:100%;
				display: flex;
				align-items: center;
			}
			.qrDiv{
				margin: 48px auto;
				width: 300px;
				border-radius: 20px;
				background: #ffffff;
				box-sizing: border-box;
				padding: 16px;
				position: relative;
				overflow: hidden;
				display: flex;
				justify-content: center;
				align-items: center;

			}
			.qrDiv img{width: 100%; }
			/*추가*/
			.iconDiv:active{
				transform: translate(2px,2px);
				border: 2px inset #a6a6a6;
			}
			.menu h2{
				color: #FFF;
				font-family: 'Pretendard';
				font-size: 20px;
				font-style: normal;
				font-weight: 600;
				line-height: 141%; /* 141.667% */
				text-align: center;
			}
			.menu p{
				color: #ddd;
				font-family: 'Pretendard';
				font-size: 14px;
				font-style: normal;
				font-weight: 400;
				line-height: 20px; /* 142.857% */
				letter-spacing: -0.35px;
				text-align: center;
			}
			.info{
				color: #ddd;
				text-align: center;
				font-family: 'Pretendard';
				font-size: 14px;
				font-style: normal;
				font-weight: 400;
				line-height: 20px; /* 142.857% */
				letter-spacing: -0.35px;
			}
		</style>
	</head>
	<title>KISCO 한국철강 통근버스 앱 설치</title>
	<body>
		<div style="display: flex; flex-wrap: wrap; justify-content: center; margin-top: 16px;">
			<img style="width: 90px;" src="/app/download/logo.png" />
		</div>
		<p class="title">KISCO 한국철강(주) 통근버스 앱 다운로드</p>
		<div class="glassBox">
			<div class="menu">
				<div>
					<h2>통근버스 - Android</h2>
					<div class="iconDiv">
						<a href="/app/download/bus/production/app-release.apk" type="application/vnd.android.package-archive">
							<img src="/app/download/and.png" style="height: 100%;">
						</a>
					</div>

						<p class="info">아이콘을 클릭하시면 다운로드가 진행됩니다.</p>
<%--						<p>Ver 1.0.0</p>--%>
						<!--qrDiv추가-->
						<div class="qrDiv">
							<img src="/app/download/BUS_ANDROID.png">
						</div>
				</div>
			</div>
		</div>
	</body>
</html>

