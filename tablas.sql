CREATE TABLE
    vehiculo(
        v_matricula INT PRIMARY KEY,
        v_nombre CHAR(50) NOT NULL,
        v_velocidad FLOAT NOT NULL,
        v_longitud DOUBLE NOT NULL
    );

CREATE TABLE
    ligero(
        l_matricula INT PRIMARY KEY,
        l_color VARCHAR(20) NOT NULL,
        l_androide VARCHAR(30) NULL,
        FOREIGN KEY (l_matricula) REFERENCES vehiculo (v_matricula)
    );

CREATE TABLE
    transporte(
        t_matricula INT PRIMARY KEY,
        t_tripulantes INT NOT NULL,
        t_pasajeros INT NOT NULL,
        FOREIGN KEY (t_matricula) REFERENCES vehiculo (v_matricula)
    );

CREATE TABLE
    cliente(
        c_identidad INT PRIMARY KEY,
        c_nombre CHAR(40) NOT NULL,
        c_especie CHAR(30) NOT NULL,
        c_genero CHAR(30)
    );

CREATE TABLE
    capitan(
        c_licencia INT PRIMARY KEY NOT NULL,
        c_nombre CHAR(40),
        c_especie CHAR(30),
        c_transporte INT NOT NULL,
        FOREIGN KEY (c_transporte) REFERENCES transporte(t_matricula)
    );

CREATE TABLE
    alquiler(
        a_id INT PRIMARY KEY NOT NULL,
        a_cliente INT NOT NULL,
        a_vehiculo INT NOT NULL,
        a_fecha DATETIME NOT NULL,
        a_ocasion VARCHAR(50),
        a_duracion INT NOT NULL,
        a_costo DOUBLE,
        FOREIGN KEY (a_cliente) REFERENCES cliente(c_identidad),
        FOREIGN KEY (a_vehiculo) REFERENCES vehiculo(v_matricula)
    );