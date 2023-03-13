CREATE OR REPLACE PACKAGE HOSPITALS_PACKAGE AS
    
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_HOSPITAL(
    v_hospitalName IN HOSPITALS.HOSPITALNAME%TYPE,
    v_hospitalAge IN HOSPITALS.HOSPITALAGE%TYPE,
    v_hospitalArea IN HOSPITALS.HOSPITALAREA%TYPE,
    v_idManager IN HOSPITALS.IDMANAGER%TYPE,
    v_idCondition IN HOSPITALS.IDCONDITION%TYPE,
    v_idDistrict IN HOSPITALS.IDDISTRICT%TYPE,
    v_idLocation IN HOSPITALS.IDLOCATION%TYPE
    );
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_HOSPITAL(
    v_idHospital IN HOSPITALS.IDHOSPITAL%TYPE,
    v_hospitalName IN HOSPITALS.HOSPITALNAME%TYPE,
    v_hospitalAge IN HOSPITALS.HOSPITALAGE%TYPE,
    v_hospitalArea IN HOSPITALS.HOSPITALAREA%TYPE,
    v_idManager IN HOSPITALS.IDMANAGER%TYPE,
    v_idCondition IN HOSPITALS.IDCONDITION%TYPE,
    v_idDistrict IN HOSPITALS.IDDISTRICT%TYPE,
    v_idLocation IN HOSPITALS.IDLOCATION%TYPE
    );
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_HOSPITAL(
    v_idHospital IN HOSPITALS.IDHOSPITAL%TYPE
    );
    
    ------------------------------------------
    -- GET ONE HOSPITAL
    ------------------------------------------
    FUNCTION GET_ONE_HOSPITAL(
        v_idHospital IN HOSPITALS.IDHOSPITAL%TYPE
    )
    RETURN HOSPITALS%ROWTYPE;
    

    ------------------------------------------
    -- GET ALL HOSPITALS
    ------------------------------------------
    FUNCTION GET_ALL_HOSPITALS RETURN SYS_REFCURSOR;

    ------------------------------------------
    -- CHECK IF MANAGER OR LOCATION IS NOT RELATED
    ------------------------------------------
    FUNCTION CHECK_MANAGER_LOCATION (
    v_idManager IN HOSPITALS.IDMANAGER%TYPE, 
    v_idLocation IN HOSPITALS.IDLOCATION%TYPE
    )
    RETURN BOOLEAN;
    
END HOSPITALS_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY HOSPITALS_PACKAGE IS
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_HOSPITAL(
    v_hospitalName IN HOSPITALS.HOSPITALNAME%TYPE,
    v_hospitalAge IN HOSPITALS.HOSPITALAGE%TYPE,
    v_hospitalArea IN HOSPITALS.HOSPITALAREA%TYPE,
    v_idManager IN HOSPITALS.IDMANAGER%TYPE,
    v_idCondition IN HOSPITALS.IDCONDITION%TYPE,
    v_idDistrict IN HOSPITALS.IDDISTRICT%TYPE,
    v_idLocation IN HOSPITALS.IDLOCATION%TYPE
    ) IS
    
    BEGIN
    
        IF NOT CHECK_MANAGER_LOCATION(v_idManager, v_idLocation) THEN
            RAISE_APPLICATION_ERROR(-20001, 'Manager or Location is already associated with another hospital.');
        END IF;
        
        INSERT 
        INTO HOSPITALS(HOSPITALNAME, HOSPITALAGE, HOSPITALAREA, IDMANAGER, IDCONDITION, IDDISTRICT, IDLOCATION) 
        VALUES (v_hospitalName, v_hospitalAge, v_hospitalArea, v_idManager, v_idCondition, v_idDistrict, v_idLocation);
        COMMIT;
        
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_HOSPITAL(
    v_idHospital IN HOSPITALS.IDHOSPITAL%TYPE,
    v_hospitalName IN HOSPITALS.HOSPITALNAME%TYPE,
    v_hospitalAge IN HOSPITALS.HOSPITALAGE%TYPE,
    v_hospitalArea IN HOSPITALS.HOSPITALAREA%TYPE,
    v_idManager IN HOSPITALS.IDMANAGER%TYPE,
    v_idCondition IN HOSPITALS.IDCONDITION%TYPE,
    v_idDistrict IN HOSPITALS.IDDISTRICT%TYPE,
    v_idLocation IN HOSPITALS.IDLOCATION%TYPE
    ) IS
    
    BEGIN
    
        IF NOT CHECK_MANAGER_LOCATION(v_idManager, v_idLocation) THEN
            RAISE_APPLICATION_ERROR(-20001, 'Manager or Location is already associated with another hospital.');
        END IF;
        
        UPDATE HOSPITALS 
        SET 
        HOSPITALNAME = v_hospitalName, HOSPITALAGE = v_hospitalAge, HOSPITALAREA = v_hospitalArea, 
        IDMANAGER = v_idManager, IDCONDITION = v_idCondition, IDDISTRICT = v_idDistrict, IDLOCATION = v_idLocation
        WHERE IDHOSPITAL = v_idHospital;
        COMMIT;
    
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_HOSPITAL(
    v_idHospital IN HOSPITALS.IDHOSPITAL%TYPE
    ) IS
    
    BEGIN 
        DELETE FROM HOSPITALS WHERE IDHOSPITAL = v_idHospital;
        COMMIT;
    END;
    
    ------------------------------------------
    -- GET ONE HOSPITAL
    ------------------------------------------
    FUNCTION GET_ONE_HOSPITAL(
        v_idHospital IN HOSPITALS.IDHOSPITAL%TYPE
    )
    RETURN HOSPITALS%ROWTYPE
    IS
        v_result HOSPITALS%ROWTYPE;
    BEGIN 
        SELECT * INTO v_result FROM HOSPITALS WHERE IDHOSPITAL = v_idHospital;
        RETURN v_result;
    END;
    

    ------------------------------------------
    -- GET ALL HOSPITALS
    ------------------------------------------
    FUNCTION GET_ALL_HOSPITALS RETURN SYS_REFCURSOR
    IS
    c_result SYS_REFCURSOR;
    
    BEGIN
        OPEN c_result FOR SELECT * FROM HOSPITALS;
        RETURN c_result;
        CLOSE c_result;
    END;
    
    ------------------------------------------
    -- CHECK IF MANAGER OR LOCATION IS NOT RELATED
    ------------------------------------------
    FUNCTION CHECK_MANAGER_LOCATION (
    v_idManager IN HOSPITALS.IDMANAGER%TYPE, 
    v_idLocation IN HOSPITALS.IDLOCATION%TYPE
    )
    RETURN BOOLEAN
    IS
        v_manager_count INTEGER;
        v_location_count INTEGER;
    BEGIN
        -- Check if manager is already associated with another hospital
        SELECT COUNT(*)
        INTO v_manager_count
        FROM HOSPITALS
        WHERE IDMANAGER = v_idManager;
        
        -- Check if location is already associated with another hospital
        SELECT COUNT(*)
        INTO v_location_count
        FROM HOSPITALS
        WHERE IDLOCATION = v_idLocation;
        
        -- Return false if manager or location is already associated with another hospital
        IF v_manager_count > 0 OR v_location_count > 0 THEN
            RETURN FALSE;
        ELSE
            RETURN TRUE;
        END IF;
        
    END CHECK_MANAGER_LOCATION;

END HOSPITALS_PACKAGE;
/