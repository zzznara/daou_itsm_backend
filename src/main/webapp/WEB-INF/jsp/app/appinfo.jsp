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
      body{
        margin: 0;
        font-family: 'Pretendard','NotoSansKR','Malgun Gothic';
      }
      .outter {
        background: #d1d1d1;
        position: fixed;
        overflow: hidden;
        height: 100vh;
        width: 100vw;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .modal {
        width: 278px;

        padding: 32px 24px 24px 24px;
        background: #ffffff;
        box-shadow: 0px 4px 4px rgba(192, 192, 192, 0.25);
        border-radius: 8px;
        position: relative;
        align-items: center;
      }
      .title {
        font-weight: bold;
        font-size: 17px;
        margin-top:0;
        font-weight: 600;
        margin-bottom: 64px;
        text-align: center;
        letter-spacing: -0.3px;
      }
      .desc {
        font-size: 12px;
        color: #777777;
      }
      .button-group {
        display: flex;
        justify-content: space-around;

      }
      .btn {
        height: 55px;
        width: 100%;
        background: #fff;
        color: #555555;
        font-size: 20px;
        font-weight: bold;
        border: 0;
        letter-spacing: -1px;
        border-radius: 6px;
      }
      .open {
        /* background: #ff4b19; */
        background: #1e54a2;
        color: #ffffff;
      }
    </style>
    <script
      src="https://code.jquery.com/jquery-1.12.4.min.js"
      integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
      crossorigin="anonymous"
    ></script>
    <script type="text/javascript">
      let letInterval;
      let letTimeout;

      function clearTimers() {
        clearInterval(letInterval);
        clearTimeout(letTimeout);
      }

      function isHideWeb() {
        if (document.webkitHidden || document.hidden) {
          clearTimers();
        }
      }

      function redirectStore() {
        //const ua = navigator.userAgent.toLowerCase();

        if (window.confirm("한국철강 TMS 앱 안내 페이지로\n이동하시겠습니까?")) {
          // location.href =
          //   ua.indexOf("android") > -1
          //     ? "https://play.google.com/store/apps/details?id=xxx"
          //     : "https://apps.apple.com/kr/app/xxx";
          window.location.href = "https://tmsm.kisco.co.kr/tms";
        }
      }

      function checkInstallApp() {
        let letInterval = setInterval(isHideWeb, 200);
        let letTimeout = setTimeout(function () {
          redirectStore();
        }, 500);
      }

      function exeDeepLink() {
        let url = "kiscotms://statusscreen";
        window.location.href = url;
      }

      function redireactApp() {
        // try {
        //   exeDeepLink();
        // } catch (e) {
        //   //console.log("앱 미실행");
        // }
        exeDeepLink();
        checkInstallApp();
      }
    </script>
  </head>
  <title>KISCO 한국철강 TMS APP</title>
  <body>
    <div class="outter">
      <div class="modal">
        <p class="title">
          한국철강 TMS 앱을 실행합니다
          <br />
        </p>
        <div class="button-group">
          <button class="open btn" onClick="redireactApp();">실행</button>
        </div>
      </div>
    </div>
  </body>
</html>
