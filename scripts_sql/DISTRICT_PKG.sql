CREATE OR REPLACE PACKAGE DISTRICTS_PACKAGE AS
    
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_DISTRICT(
    v_districtDescription IN DISTRICTS.DISTRICTDESC%TYPE,
    v_idProvince IN DISTRICTS.IDPROVINCE%TYPE
    );
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_DISTRICT(
    v_idDistrict IN DISTRICTS.IDDISTRICT%TYPE,
    v_districtDescription IN DISTRICTS.DISTRICTDESC%TYPE,
    v_idProvince IN DISTRICTS.IDPROVINCE%TYPE
    );
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_DISTRICT(
    v_idDistrict IN DISTRICTS.IDDISTRICT%TYPE
    );
    
    ------------------------------------------
    -- GET ONE DISTRICT
    ------------------------------------------
    FUNCTION GET_ONE_DISTRICT(
        v_idDistrict IN DISTRICTS.IDDISTRICT%TYPE
    )
    RETURN DISTRICTS%ROWTYPE;
    

    ------------------------------------------
    -- GET ALL DISTRICTS
    ------------------------------------------
    FUNCTION GET_ALL_DISTRICTS RETURN SYS_REFCURSOR;

END DISTRICTS_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY DISTRICTS_PACKAGE IS
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_DISTRICT(
    v_districtDescription IN DISTRICTS.DISTRICTDESC%TYPE,
    v_idProvince IN DISTRICTS.IDPROVINCE%TYPE
    ) IS
    
    BEGIN
        INSERT INTO DISTRICTS(DISTRICTDESC, IDPROVINCE) VALUES (v_districtDescription, v_idProvince);
        COMMIT;
    END;
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_DISTRICT(
    v_idDistrict IN DISTRICTS.IDDISTRICT%TYPE,
    v_districtDescription IN DISTRICTS.DISTRICTDESC%TYPE,
    v_idProvince IN DISTRICTS.IDPROVINCE%TYPE
    ) IS
    
    BEGIN
        UPDATE DISTRICTS 
        SET DISTRICTDESC = v_districtDescription, IDPROVINCE = v_idProvince
        WHERE IDDISTRICT = v_idDistrict;
        COMMIT;
    END;
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_DISTRICT(
    v_idDistrict IN DISTRICTS.IDDISTRICT%TYPE
    ) IS
    
    BEGIN 
        DELETE FROM DISTRICTS WHERE IDDISTRICT = v_idDistrict;
        COMMIT;
    END;
    
    ------------------------------------------
    -- GET ONE DISTRICT
    ------------------------------------------
    FUNCTION GET_ONE_DISTRICT(
        v_idDistrict IN DISTRICTS.IDDISTRICT%TYPE
    )
    RETURN DISTRICTS%ROWTYPE
    IS
        v_result DISTRICTS%ROWTYPE;
    BEGIN 
        SELECT * INTO v_result FROM DISTRICTS WHERE IDDISTRICT = v_idDistrict;
        RETURN v_result;
    END;
    

    ------------------------------------------
    -- GET ALL DISTRICTS
    ------------------------------------------
    FUNCTION GET_ALL_DISTRICTS RETURN SYS_REFCURSOR
    IS
    c_result SYS_REFCURSOR;
    
    BEGIN
        OPEN c_result FOR SELECT * FROM DISTRICTS;
        RETURN c_result;
    END;

END DISTRICTS_PACKAGE;
/