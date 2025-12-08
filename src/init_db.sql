create database product_management_db
create user product_manager_user with password '123456';
\c product_management_db;
GRANT ALL PRIVILEGES ON DATABASE product_management_db to product_manager_user;
GRANT ALL ON SCHEMA public TO product_manager_user;
GRANT ALL ON ALL TABLES IN SCHEMA public to product_manager_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO product_manager_user;
