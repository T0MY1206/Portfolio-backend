-- ============================================
-- DATOS INICIALES PARA EL PORTFOLIO
-- ============================================
-- Este script se ejecuta automáticamente al iniciar la aplicación
-- Solo inserta datos si las tablas están vacías

-- ============================================
-- NOTA: Si hay un constraint check antiguo, eliminarlo manualmente con:
-- ALTER TABLE technologies DROP CONSTRAINT IF EXISTS technologies_type_check;
-- ============================================

-- ============================================
-- MIGRACIÓN: Agregar columnas de traducción a projects
-- ============================================
-- Estas columnas permiten almacenar traducciones de nombre y descripción
-- Si las columnas ya existen, estos comandos no fallarán (dependiendo del SGBD)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns 
                   WHERE table_name = 'projects' AND column_name = 'name_en') THEN
        ALTER TABLE projects ADD COLUMN name_en VARCHAR(255);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns 
                   WHERE table_name = 'projects' AND column_name = 'name_es') THEN
        ALTER TABLE projects ADD COLUMN name_es VARCHAR(255);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns 
                   WHERE table_name = 'projects' AND column_name = 'description_en') THEN
        ALTER TABLE projects ADD COLUMN description_en TEXT;
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns 
                   WHERE table_name = 'projects' AND column_name = 'description_es') THEN
        ALTER TABLE projects ADD COLUMN description_es TEXT;
    END IF;
END $$;

-- ============================================
-- PROFILE (Información Personal)
-- ============================================
INSERT INTO profiles (id, full_name, title, location, email, phone, summary)
SELECT 1, 'Tomas Tutor Onetto', 'Software Developer & Technical Leader', 
        'San Vicente, Buenos Aires, Argentina', 'tomas2000tutor@gmail.com', 
        '+54 2224 445207', 
        'Software Developer with 3+ years of experience in frontend, backend, software architecture and technical leadership. Strong background in building scalable web and business systems.'
WHERE NOT EXISTS (SELECT 1 FROM profiles WHERE id = 1);

-- Resetear secuencia de profiles
SELECT setval('profiles_id_seq', COALESCE((SELECT MAX(id) FROM profiles), 1), true);

-- ============================================
-- EDUCATION (Educación)
-- ============================================
INSERT INTO educations (id, degree, institution, status, start_date, end_date, description)
SELECT 1, 'Systems Analyst, Application Developer and Software Programmer', 
        'ISFDyT N°93', 'In Progress', '2020-01-01', NULL, 
        'Carrera en curso de análisis de sistemas y desarrollo de software'
WHERE NOT EXISTS (SELECT 1 FROM educations WHERE id = 1);

SELECT setval('educations_id_seq', COALESCE((SELECT MAX(id) FROM educations), 1), true);

-- ============================================
-- LANGUAGES (Idiomas)
-- ============================================
INSERT INTO languages (id, name, level)
SELECT 1, 'Spanish', 'Native'
WHERE NOT EXISTS (SELECT 1 FROM languages WHERE id = 1);

INSERT INTO languages (id, name, level)
SELECT 2, 'English', 'Basic/Intermediate'
WHERE NOT EXISTS (SELECT 1 FROM languages WHERE id = 2);

INSERT INTO languages (id, name, level)
SELECT 3, 'Italian', 'Basic'
WHERE NOT EXISTS (SELECT 1 FROM languages WHERE id = 3);

SELECT setval('languages_id_seq', COALESCE((SELECT MAX(id) FROM languages), 1), true);

-- ============================================
-- EXPERIENCES (Experiencia Laboral)
-- ============================================
INSERT INTO experiences (id, title, company, start_date, end_date, is_current, description)
SELECT 1, 'Technical Leader', 'ThankIt', '2023-01-01', NULL, true, 
       'Leading technical initiatives and development teams.'
WHERE NOT EXISTS (SELECT 1 FROM experiences WHERE id = 1);

INSERT INTO experiences (id, title, company, start_date, end_date, is_current, description)
SELECT 2, 'Teaching Assistant', 'ISFDyT N°93', '2022-01-01', NULL, true, 
       'Assisting in educational programs and student support.'
WHERE NOT EXISTS (SELECT 1 FROM experiences WHERE id = 2);

INSERT INTO experiences (id, title, company, start_date, end_date, is_current, description)
SELECT 3, 'IT Support', 'Estudio Contadores Bono', '2020-01-01', '2022-12-31', false, 
       'Providing technical support and IT solutions.'
WHERE NOT EXISTS (SELECT 1 FROM experiences WHERE id = 3);

SELECT setval('experiences_id_seq', COALESCE((SELECT MAX(id) FROM experiences), 1), true);

-- ============================================
-- TECHNOLOGIES (Tecnologías con años de experiencia)
-- ============================================
-- Frontend
INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 1, 'HTML', 'FRONTEND', 4, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 1);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 2, 'CSS', 'FRONTEND', 4, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 2);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 3, 'JavaScript', 'FRONTEND', 4, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 3);

-- Backend
INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 4, 'C#', 'BACKEND', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 4);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 5, '.NET', 'BACKEND', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 5);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 6, 'Node.js', 'BACKEND', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 6);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 7, 'Python', 'BACKEND', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 7);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 8, 'Java', 'BACKEND', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 8);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 9, 'Spring Boot', 'BACKEND', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 9);

-- Databases
INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 10, 'SQL Server', 'DATABASE', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 10);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 11, 'PostgreSQL', 'DATABASE', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 11);

-- Tools
INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 12, 'GitHub', 'TOOL', 4, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 12);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 13, 'Jira', 'TOOL', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 13);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 14, 'Postman', 'TOOL', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 14);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 15, 'REST APIs', 'TOOL', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 15);

SELECT setval('technologies_id_seq', COALESCE((SELECT MAX(id) FROM technologies), 1), true);

-- ============================================
-- PROJECTS (Proyectos)
-- ============================================
INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 1, 'Portfolio React', 'Modern portfolio built with React, TypeScript, and Vite', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 1);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 2, 'Portfolio Angular', 'Professional portfolio using Angular standalone components', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 2);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 3, 'Portfolio Mobile', 'Cross-platform mobile portfolio with React Native and Expo', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 3);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 4, 'Spring Boot Backend', 'Microservice with Spring Boot, Tomcat, JPA/Hibernate, Swagger, SQL', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 4);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 5, 'Camunda 7 BPM Frontend', 'BPM/DMN flow with AngularJS forms, PostgreSQL, and custom Camunda UI', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 5);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 6, 'Proyecto Pronafe', 'Sistema de gestión educativa con PHP/Laravel, MySQL y tecnologías web modernas', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 6);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 7, 'BE Generico', 'Sistema backend genérico con APIs REST e integración de base de datos', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 7);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 8, 'URL Shortener (.NET)', 'API mínima para acortar URLs con documentación Scalar, OpenAPI (ES/EN) y tema oscuro/claro', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 8);

INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 9, 'Bocato Pasteleria', 'Sistema web para pastelería con Angular, TypeScript y diseño moderno', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 9);

SELECT setval('projects_id_seq', COALESCE((SELECT MAX(id) FROM projects), 1), true);

-- ============================================
-- TECHNOLOGIES ADICIONALES PARA PROYECTOS
-- ============================================
INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 16, 'React', 'FRONTEND', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 16);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 17, 'TypeScript', 'FRONTEND', 3, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 17);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 18, 'Vite', 'TOOL', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 18);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 19, 'Angular', 'FRONTEND', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 19);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 20, 'React Native', 'FRONTEND', 1, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 20);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 21, 'Expo', 'TOOL', 1, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 21);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 22, 'JPA', 'BACKEND', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 22);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 23, 'Swagger', 'TOOL', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 23);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 24, 'Camunda 7', 'TOOL', 1, false WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 24);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 25, 'AngularJS', 'FRONTEND', 2, false WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 25);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 26, 'PHP', 'BACKEND', 2, false WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 26);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 27, 'Laravel', 'BACKEND', 2, false WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 27);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 28, 'MySQL', 'DATABASE', 2, false WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 28);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 29, 'OpenAPI', 'TOOL', 2, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 29);

INSERT INTO technologies (id, name, type, years_of_experience, is_currently_used)
SELECT 30, 'Scalar', 'TOOL', 1, true WHERE NOT EXISTS (SELECT 1 FROM technologies WHERE id = 30);

SELECT setval('technologies_id_seq', COALESCE((SELECT MAX(id) FROM technologies), 1), true);

-- ============================================
-- PROJECT_TECHNOLOGIES (Relación ManyToMany)
-- ============================================
-- Portfolio React
INSERT INTO project_technologies (project_id, technology_id)
SELECT 1, 16 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 1 AND technology_id = 16);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 1, 17 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 1 AND technology_id = 17);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 1, 18 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 1 AND technology_id = 18);

-- Portfolio Angular
INSERT INTO project_technologies (project_id, technology_id)
SELECT 2, 19 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 2 AND technology_id = 19);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 2, 17 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 2 AND technology_id = 17);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 2, 2 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 2 AND technology_id = 2);

-- Portfolio Mobile
INSERT INTO project_technologies (project_id, technology_id)
SELECT 3, 20 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 3 AND technology_id = 20);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 3, 21 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 3 AND technology_id = 21);

-- Spring Boot Backend
INSERT INTO project_technologies (project_id, technology_id)
SELECT 4, 9 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 4 AND technology_id = 9);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 4, 22 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 4 AND technology_id = 22);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 4, 23 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 4 AND technology_id = 23);

-- Camunda 7 BPM Frontend
INSERT INTO project_technologies (project_id, technology_id)
SELECT 5, 24 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 5 AND technology_id = 24);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 5, 25 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 5 AND technology_id = 25);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 5, 11 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 5 AND technology_id = 11);

-- Proyecto Pronafe
INSERT INTO project_technologies (project_id, technology_id)
SELECT 6, 26 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 6 AND technology_id = 26);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 6, 27 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 6 AND technology_id = 27);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 6, 28 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 6 AND technology_id = 28);

-- BE Generico
INSERT INTO project_technologies (project_id, technology_id)
SELECT 7, 9 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 7 AND technology_id = 9);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 7, 15 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 7 AND technology_id = 15);

-- URL Shortener (.NET)
INSERT INTO project_technologies (project_id, technology_id)
SELECT 8, 5 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 8 AND technology_id = 5);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 8, 29 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 8 AND technology_id = 29);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 8, 30 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 8 AND technology_id = 30);

-- Bocato Pasteleria
INSERT INTO project_technologies (project_id, technology_id)
SELECT 9, 19 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 9 AND technology_id = 19);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 9, 17 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 9 AND technology_id = 17);
