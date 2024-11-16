/*
4. mostrar detalhes do jogo para visualização rápida
> será útil para quando o usuário fizer um hover na capa do jogo, mostrar detalhes do jogo (dados do jogo)
(ii) junção externa (left join)
*/
SELECT 
    J.nome AS nome_jogo,
    J.data_lancamento,
    CF.nome AS categoria
FROM Jogo J
LEFT JOIN (
    SELECT 
        JC.fk_Jogo_id, 
        C.nome,
        ROW_NUMBER() OVER (PARTITION BY JC.fk_Jogo_id ORDER BY C.nome) AS rn
    FROM JogoCategoria JC
    JOIN Categoria C ON JC.fk_Categoria_id = C.id
) CF ON CF.fk_Jogo_id = J.id
WHERE J.id = '20920'
  AND CF.rn <= 5;