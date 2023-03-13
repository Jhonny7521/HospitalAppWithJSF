CREATE OR REPLACE PACKAGE LOCATIONS_PACKAGE AS
    
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_LOCATION(
    v_locationDescription IN LOCATIONS.LOCATIONDESC%TYPE
    );
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_LOCATION(
    v_idLocation IN LOCATIONS.IDLOCATION%TYPE,
    v_locationDescription IN LOCATIONS.LOCATIONDESC%TYPE
    );
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_LOCATION(
    v_idLocation IN LOCATIONS.IDLOCATION%TYPE
    );
    
    ------------------------------------------
    -- GET ONE LOCATION
    ------------------------------------------
    FUNCTION GET_ONE_LOCATION(
        v_idLocation IN LOCATIONS.IDLOCATION%TYPE
    )
    RETURN LOCATIONS%ROWTYPE;
    

    ------------------------------------------
    -- GET ALL LOCATIONS
    ------------------------------------------
    FUNCTION GET_ALL_LOCATIONS RETURN SYS_REFCURSOR;

END LOCATIONS_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY LOCATIONS_PACKAGE IS
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_LOCATION(
    v_locationDescription IN LOCATIONS.LOCATIONDESC%TYPE
    ) IS
    
    BEGIN
        INSERT INTO LOCATIONS(LOCATIONDESC) VALUES (v_locationDescription);
        COMMIT;
    END;
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_LOCATION(
    v_idLocation IN LOCATIONS.IDLOCATION%TYPE,
    v_locationDescription IN LOCATIONS.LOCATIONDESC%TYPE
    ) IS
    
    BEGIN
        UPDATE LOCATIONS 
        SET LOCATIONDESC = v_locationDescription
        WHERE IDLOCATION = v_idLocation;
        COMMIT;
    END;
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_LOCATION(
    v_idLocation IN LOCATIONS.IDLOCATION%TYPE
    ) IS
    
    BEGIN 
        DELETE FROM LOCATIONS WHERE IDLOCATION = v_idLocation;
        COMMIT;
    END;
    
    ------------------------------------------
    -- GET ONE LOCATION
    ------------------------------------------
    FUNCTION GET_ONE_LOCATION(
        v_idLocation IN LOCATIONS.IDLOCATION%TYPE
    )
    RETURN LOCATIONS%ROWTYPE
    IS
        v_result LOCATIONS%ROWTYPE;
    BEGIN 
        SELECT * INTO v_result FROM LOCATIONS WHERE IDLOCATION = v_idLocation;
        RETURN v_result;
    END;
    

    ------------------------------------------
    -- GET ALL LOCATIONS
    ------------------------------------------
    FUNCTION GET_ALL_LOCATIONS RETURN SYS_REFCURSOR
    IS
    c_result SYS_REFCURSOR;
    
    BEGIN
        OPEN c_result FOR SELECT * FROM LOCATIONS;
        RETURN c_result;
        CLOSE c_result;
    END;

END LOCATIONS_PACKAGE;
/