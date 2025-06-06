DO $$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'fenix') THEN
      CREATE USER fenix WITH PASSWORD 'fenix123';
END IF;
END
$$;

-- Tạo database mới
CREATE DATABASE fenixdb;

-- Cấp quyền cho user trên database
GRANT ALL PRIVILEGES ON DATABASE fenixdb TO fenix;
