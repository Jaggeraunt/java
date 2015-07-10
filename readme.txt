Для работы проекта необходимо добавить следующий ресурс в файл
tomcat/config/context.xml внутрь тега <context>

Также необходимо создать базу и выполнить в ней файл personnel_department.sql

<Resource name="jdbc/PersonnelDB" auth="Container" type="javax.sql.DataSource"
   maxActive="100" maxIdle="30" maxWait="10000"
   username="username" password="password" driverClassName="com.mysql.jdbc.Driver"
   url="jdbc:mysql://localhost:3306/personnel_department"/>