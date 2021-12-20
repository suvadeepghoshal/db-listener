CREATE OR REPLACE FUNCTION public.notify_change() RETURNS TRIGGER AS $$
BEGIN
	PERFORM pg_notify(CAST('update_notification' AS text), row_to_json(NEW)::text);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
