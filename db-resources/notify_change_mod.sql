CREATE OR REPLACE FUNCTION public.notify_change_mod() RETURNS TRIGGER AS $$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		PERFORM pg_notify(CAST('delete_notification' AS text), row_to_json(OLD)::text);
    	RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
    PERFORM pg_notify(CAST('update_notification' AS text), row_to_json(NEW)::text);
    	RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
    PERFORM pg_notify(CAST('insert_notification' AS text), row_to_json(NEW)::text);
    	RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;