CREATE OR REPLACE PACKAGE MANAGERS_PACKAGE AS
    
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_MANAGER(
    v_managerDescription IN MANAGERS.MANAGERDESC%TYPE
    );
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_MANAGER(
    v_idManager IN MANAGERS.IDMANAGER%TYPE,
    v_managerDescription IN MANAGERS.MANAGERDESC%TYPE
    );
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_MANAGER(
    v_idManager IN MANAGERS.IDMANAGER%TYPE
    );
    
    ------------------------------------------
    -- GET ONE MANAGER
    ------------------------------------------
    FUNCTION GET_ONE_MANAGER(
        v_idManager IN MANAGERS.IDMANAGER%TYPE
    )
    RETURN MANAGERS%ROWTYPE;
    

    ------------------------------------------
    -- GET ALL MANAGERS
    ------------------------------------------
    FUNCTION GET_ALL_MANAGERS RETURN SYS_REFCURSOR;

END MANAGERS_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY MANAGERS_PACKAGE IS
    ------------------------------------------
    -- INSERT
    ------------------------------------------
    PROCEDURE INSERT_MANAGER(
    v_managerDescription IN MANAGERS.MANAGERDESC%TYPE
    ) IS
    
    BEGIN
        INSERT INTO MANAGERS(MANAGERDESC) VALUES (v_managerDescription);
        COMMIT;
    END;
    
    ------------------------------------------
    -- UPDATE
    ------------------------------------------
    PROCEDURE UPDATE_MANAGER(
    v_idManager IN MANAGERS.IDMANAGER%TYPE,
    v_managerDescription IN MANAGERS.MANAGERDESC%TYPE
    ) IS
    
    BEGIN
        UPDATE MANAGERS 
        SET MANAGERDESC = v_managerDescription
        WHERE IDMANAGER = v_idManager;
        COMMIT;
    END;
    
    ------------------------------------------
    -- DELETE
    ------------------------------------------
    PROCEDURE DELETE_MANAGER(
    v_idManager IN MANAGERS.IDMANAGER%TYPE
    ) IS
    
    BEGIN 
        DELETE FROM MANAGERS WHERE IDMANAGER = v_idManager;
        COMMIT;
    END;
    
    ------------------------------------------
    -- GET ONE MANAGER
    ------------------------------------------
    FUNCTION GET_ONE_MANAGER(
        v_idManager IN MANAGERS.IDMANAGER%TYPE
    )
    RETURN MANAGERS%ROWTYPE
    IS
        v_result MANAGERS%ROWTYPE;
    BEGIN 
        SELECT * INTO v_result FROM MANAGERS WHERE IDMANAGER = v_idManager;
        RETURN v_result;
    END;
    

    ------------------------------------------
    -- GET ALL MANAGERS
    ------------------------------------------
    FUNCTION GET_ALL_MANAGERS RETURN SYS_REFCURSOR
    IS
    c_result SYS_REFCURSOR;
    
    BEGIN
        OPEN c_result FOR SELECT * FROM MANAGERS;
        RETURN c_result;
        CLOSE c_result;
    END;

END MANAGERS_PACKAGE;
/