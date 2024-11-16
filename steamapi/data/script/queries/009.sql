/*
9. trazer todas as categorias de um jogo
> será útil para tela de detalhes do jogo
*/
SELECT 
    C.nome AS nome_categoria
FROM 
    Categoria C
JOIN 
    JogoCategoria JC ON C.id = JC.fk_Categoria_id
WHERE 
    JC.fk_Jogo_id = '564310';