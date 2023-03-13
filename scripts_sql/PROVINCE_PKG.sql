CREATE OR REPLACE PACKAGE PROVINCES_PACKAGE AS
    
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_PROVINCE(
    v_provinceDescription IN PROVINCES.PROVINCEDESC%TYPE
    );
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_PROVINCE(
    v_idProvince IN PROVINCES.IDPROVINCE%TYPE,
    v_provinceDescription IN PROVINCES.PROVINCEDESC%TYPE        
    );
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_PROVINCE(
    v_idProvince IN PROVINCES.IDPROVINCE%TYPE
    );
    
    ------------------------------------------
    -- GET ONE PROVINCE
    ------------------------------------------
    FUNCTION GET_ONE_PROVINCE(
        v_idProvince IN PROVINCES.IDPROVINCE%TYPE
    )
    RETURN SYS_REFCURSOR;
    

    ------------------------------------------
    -- GET ALL PROVINCES
    ------------------------------------------
    FUNCTION GET_ALL_PROVINCES RETURN SYS_REFCURSOR;

END PROVINCES_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY PROVINCES_PACKAGE IS
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_PROVINCE(
    v_provinceDescription IN PROVINCES.PROVINCEDESC%TYPE
    ) IS
    
    BEGIN
        INSERT INTO PROVINCES(PROVINCEDESC) VALUES (v_provinceDescription);
        COMMIT;
    END;
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_PROVINCE(
    v_idProvince IN PROVINCES.IDPROVINCE%TYPE,
    v_provinceDescription IN PROVINCES.PROVINCEDESC%TYPE        
    ) IS
    
    BEGIN
        UPDATE PROVINCES SET PROVINCEDESC = v_provinceDescription WHERE IDPROVINCE = v_idProvince;
        COMMIT;
    END;
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_PROVINCE(
    v_idProvince IN PROVINCES.IDPROVINCE%TYPE
    ) IS
    
    BEGIN 
        DELETE FROM PROVINCES WHERE IDPROVINCE = v_idProvince;
        COMMIT;
    END;
    
    ------------------------------------------
    -- GET ONE PROVINCE
    ------------------------------------------
    FUNCTION GET_ONE_PROVINCE(
        v_idProvince IN PROVINCES.IDPROVINCE%TYPE
    )
    RETURN SYS_REFCURSOR
    IS
        v_cursor SYS_REFCURSOR;
    BEGIN 
        OPEN v_cursor FOR SELECT * FROM PROVINCES WHERE IDPROVINCE = v_idProvince;
        RETURN v_cursor;
    END;
    

    ------------------------------------------
    -- GET ALL PROVINCES
    ------------------------------------------
    FUNCTION GET_ALL_PROVINCES RETURN SYS_REFCURSOR
    IS
    c_result SYS_REFCURSOR;
    
    BEGIN
        OPEN c_result FOR SELECT * FROM PROVINCES;
        RETURN c_result;
    END;

END PROVINCES_PACKAGE;
