-- ============================================
-- SCRIPT PARA CORREGIR/COMPLETAR DATOS
-- ============================================
-- Ejecutar este script si los datos no se cargaron correctamente

-- Verificar y insertar Portfolio React si no existe
INSERT INTO projects (id, name, description, image_url, repository_url, live_url)
SELECT 1, 'Portfolio React', 'Modern portfolio built with React, TypeScript, and Vite', 
       NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM projects WHERE id = 1);

-- Asegurar que Portfolio React tenga sus tecnologías
INSERT INTO project_technologies (project_id, technology_id)
SELECT 1, 16 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 1 AND technology_id = 16);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 1, 17 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 1 AND technology_id = 17);

INSERT INTO project_technologies (project_id, technology_id)
SELECT 1, 18 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 1 AND technology_id = 18);

-- Asegurar que Portfolio Angular tenga Angular
INSERT INTO project_technologies (project_id, technology_id)
SELECT 2, 19 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 2 AND technology_id = 19);

-- Asegurar que Portfolio Mobile tenga React Native
INSERT INTO project_technologies (project_id, technology_id)
SELECT 3, 20 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 3 AND technology_id = 20);

-- Asegurar que Spring Boot Backend tenga Spring Boot
INSERT INTO project_technologies (project_id, technology_id)
SELECT 4, 9 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 4 AND technology_id = 9);

-- Asegurar que Camunda 7 BPM Frontend tenga Camunda 7
INSERT INTO project_technologies (project_id, technology_id)
SELECT 5, 24 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 5 AND technology_id = 24);

-- Asegurar que Proyecto Pronafe tenga PHP
INSERT INTO project_technologies (project_id, technology_id)
SELECT 6, 26 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 6 AND technology_id = 26);

-- Asegurar que BE Generico tenga Spring Boot
INSERT INTO project_technologies (project_id, technology_id)
SELECT 7, 9 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 7 AND technology_id = 9);

-- Asegurar que URL Shortener tenga .NET
INSERT INTO project_technologies (project_id, technology_id)
SELECT 8, 5 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 8 AND technology_id = 5);

-- Asegurar que Bocato Pasteleria tenga Angular
INSERT INTO project_technologies (project_id, technology_id)
SELECT 9, 19 WHERE NOT EXISTS (SELECT 1 FROM project_technologies WHERE project_id = 9 AND technology_id = 19);
