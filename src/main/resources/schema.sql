 create table city_master (
       city_id integer not null auto_increment,
        city_name varchar(255),
        state_id integer,
        primary key (city_id)
    ) ;
    
    create table country_master (
       country_id integer not null auto_increment,
        country_name varchar(255),
        primary key (country_id)
    )  ;   
    create table role_tab (
       role varchar(255) not null,
        role_desrciption varchar(255),
        primary key (role)
    ) ;
    create table state_master (
       state_id integer not null auto_increment,
        state_name varchar(255),
        country_id integer,
        primary key (state_id)
    ); 
    
    create table user_tab (
       user_id integer not null auto_increment,
        city varchar(255),
        country varchar(255),
        dob datetime(6),
        email varchar(255),
        first_name varchar(255),
        gender varchar(255),
        last_name varchar(255),
        mobile_no bigint not null,
        password varchar(255),
        state varchar(255),
        status varchar(255),
        primary key (user_id)
    )  ;
    
    create table user_tab_roles (
       user_id integer not null,
        role varchar(255) not null,
        primary key (user_id, role)
    )  ;
    
    alter table city_master 
       add constraint city_state_fk
       foreign key (state_id) 
       references state_master (state_id);

    
    alter table state_master 
       add constraint state_country_fk
       foreign key (country_id) 
       references country_master (country_id);
 
    
    alter table user_tab_roles 
       add constraint role_fk
       foreign key (role) 
       references role_tab (role);

    
    alter table user_tab_roles 
       add constraint user_fk
       foreign key (user_id) 
       references user_tab (user_id);