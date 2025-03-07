EXPLAIN PLAN FOR
SELECT user_id FROM TB_USER WHERE user_id = 'admin';

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);


SELECT * FROM TB_USER
WHERE user_id = 'admin';

UPDATE VUE.TB_USER
SET PWD = '$2a$10$0CQjVTUmr9D3wRHxYJxXhe2il6ujPK/x2q6NohJSMTmTX.Sl5ZARW'
WHERE USER_ID = 'admin';

COMMIT;

SELECT * FROM TB_USER where user_id='admin';

  SELECT *
        FROM VUE.TB_USER
        WHERE USER_ID = 'admin'
        AND USE_YN = 'Y';

SELECT * FROM VUE.TB_USER WHERE USER_ID = 'admin' AND USE_YN = 'Y';


SELECT
    TU.SYSTEM_CD,
    TRIM(TU.USER_ID) AS USER_ID,
    TU.EMP_NO,
    TU.NM_KOR,
    TU.NM_CHN,
    TU.EMAIL,
    TU.DPT_CD,
    TU.DPT_NM,
    TU.TEL_NO,
    TU.MOBILE_NO,
    TU.RSOF_CD,
    TU.RSOF_NM,
    TU.PLBS_CD,
    TU.PWD,
    TU.PWD_CHNG_DT,
    TU.GENDER,
    TU.ETCO_DT,
    TU.USE_YN,
    TU.NM_ENG,


    (SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END
     FROM VUE.TB_GROUP_USER TGU
     INNER JOIN VUE.TB_GROUP TG ON TGU.GROUP_ID = TG.GROUP_ID
     WHERE TGU.USER_ID = TU.USER_ID
     AND TG.GROUP_NM IN ('관리자', '담당자', '일반담당자', '외부업체', '보안담당자', 'RPA', '일반임직원')) AS IS_ADMIN,

    -- 사용자가 속한 그룹명을 리스트로 가져오기
    (SELECT LISTAGG(TG.GROUP_NM, ', ') WITHIN GROUP (ORDER BY TG.GROUP_NM)
     FROM VUE.TB_GROUP_USER TGU
     INNER JOIN VUE.TB_GROUP TG ON TGU.GROUP_ID = TG.GROUP_ID
     WHERE TGU.USER_ID = TU.USER_ID) AS ADMIN_NM,

    '0000' AS LOGIN_RESULT
    FROM VUE.TB_USER TU
    WHERE TU.USER_ID = 'admin'
    AND TU.USE_YN = 'Y';


SELECT USER_ID, PWD, USE_YN FROM VUE.TB_USER WHERE USER_ID = 'admin';
UPDATE VUE.TB_USER SET USE_YN = 'Y' WHERE USER_ID = 'admin';


    SELECT
        TU.SYSTEM_CD,
        TRIM(TU.USER_ID) AS USER_ID,
        TU.EMP_NO,
        TU.NM_KOR,
        TU.NM_CHN,
        TU.EMAIL,
        TU.DPT_CD,
        TU.DPT_NM,
        TU.TEL_NO,
        TU.MOBILE_NO,
        TU.RSOF_CD,
        TU.RSOF_NM,
        TU.PLBS_CD,
        TU.PWD,
        TU.PWD_CHNG_DT,
        TU.GENDER,
        TU.ETCO_DT,
        TU.USE_YN,
        TU.NM_ENG,

        -- 사용자가 특정 그룹에 속하는지 확인 (기존 방식으로 복구)
        (SELECT DECODE(COUNT(*), 0, 'N', 'Y')
        FROM VUE.TB_GROUP_USER TGU
        INNER JOIN VUE.TB_GROUP TG ON TGU.GROUP_ID = TG.GROUP_ID
        WHERE TGU.USER_ID = TU.USER_ID
          AND TG.GROUP_NM IN ('관리자', '담당자', '일반담당자', '외부업체', '보안담당자', 'RPA', '일반임직원')) AS IS_ADMIN,

        -- 사용자가 속한 그룹명을 리스트로 가져오기
        (SELECT LISTAGG(TG.GROUP_NM, ', ') WITHIN GROUP (ORDER BY TGU.USER_ID)
        FROM VUE.TB_GROUP_USER TGU
        INNER JOIN VUE.TB_GROUP TG ON TGU.GROUP_ID = TG.GROUP_ID
        WHERE TGU.USER_ID = TU.USER_ID) AS ADMIN_NM,

        '0000' AS LOGIN_RESULT

        FROM VUE.TB_USER TU
        WHERE TU.USER_ID = 'admin'
        AND TU.USE_YN = 'Y';


SELECT * FROM VUE.TB_USER WHERE UPPER(USER_ID) = UPPER('admin');
