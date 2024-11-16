/*
7. trazer todos os detalhes do jogo e número de conquistas
> será útil para tela de detalhes do jogo
*/
SELECT 
    J.*,
    COUNT(C.id) AS total_conquistas
FROM Jogo J
LEFT JOIN Conquista C ON J.id = C.fk_Jogo_id
WHERE J.id = '20920'
GROUP BY J.id;