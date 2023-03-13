CREATE OR REPLACE PACKAGE CONDITIONS_PACKAGE AS
    
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_CONDITION(
    v_conditionDescription IN CONDITIONS.CONDITIONDESC%TYPE
    );
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_CONDITION(
    v_idCondition IN CONDITIONS.IDCONDITION%TYPE,
    v_conditionDescription IN CONDITIONS.CONDITIONDESC%TYPE
    );
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_CONDITION(
    v_idCondition IN CONDITIONS.IDCONDITION%TYPE
    );
    
    ------------------------------------------
    -- GET ONE CONDITION
    ------------------------------------------
    FUNCTION GET_ONE_CONDITION(
        v_idCondition IN CONDITIONS.IDCONDITION%TYPE
    )
    RETURN CONDITIONS%ROWTYPE;
    

    ------------------------------------------
    -- GET ALL CONDITIONS
    ------------------------------------------
    FUNCTION GET_ALL_CONDITIONS RETURN SYS_REFCURSOR;

END CONDITIONS_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY CONDITIONS_PACKAGE IS
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_CONDITION(
    v_conditionDescription IN CONDITIONS.CONDITIONDESC%TYPE
    ) IS
    
    BEGIN
        INSERT INTO CONDITIONS(CONDITIONDESC) VALUES (v_conditionDescription);
        COMMIT;
    END;
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_CONDITION(
    v_idCondition IN CONDITIONS.IDCONDITION%TYPE,
    v_conditionDescription IN CONDITIONS.CONDITIONDESC%TYPE
    ) IS
    
    BEGIN
        UPDATE CONDITIONS 
        SET CONDITIONDESC = v_conditionDescription
        WHERE IDCONDITION = v_idCondition;
        COMMIT;
    END;
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_CONDITION(
    v_idCondition IN CONDITIONS.IDCONDITION%TYPE
    ) IS
    
    BEGIN 
        DELETE FROM CONDITIONS WHERE IDCONDITION = v_idCondition;
        COMMIT;
    END;
    
    ------------------------------------------
    -- GET ONE CONDITION
    ------------------------------------------
    FUNCTION GET_ONE_CONDITION(
        v_idCondition IN CONDITIONS.IDCONDITION%TYPE
    )
    RETURN CONDITIONS%ROWTYPE
    IS
        v_result CONDITIONS%ROWTYPE;
    BEGIN 
        SELECT * INTO v_result FROM CONDITIONS WHERE IDCONDITION = v_idCondition;
        RETURN v_result;
    END;
    

    ------------------------------------------
    -- GET ALL CONDITIONS
    ------------------------------------------
    FUNCTION GET_ALL_CONDITIONS RETURN SYS_REFCURSOR
    IS
    c_result SYS_REFCURSOR;
    
    BEGIN
        OPEN c_result FOR SELECT * FROM CONDITIONS;
        RETURN c_result;
        CLOSE c_result;
    END;

END CONDITIONS_PACKAGE;
/