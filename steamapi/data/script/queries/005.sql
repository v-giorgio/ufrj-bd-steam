/*
5. quantidade de jogos que usuario possui por categoria
- (i) mais de uma junção
- (ii) agregação com agrupamento
*/
SELECT
    U.nome as usuario_nome,
    U.apelido AS usuario_apelido,
    C.nome AS categoria,
    COUNT(*) AS quantidade_de_vezes_jogado
FROM 
    Usuario U
JOIN 
    JogoUsuario JU ON U.id = JU.fk_Usuario_id
JOIN 
    Jogo J ON JU.fk_Jogo_id = J.id
JOIN 
    JogoCategoria JC ON J.id = JC.fk_Jogo_id
JOIN 
    Categoria C ON JC.fk_Categoria_id = C.id
WHERE 
    U.id = '76561198098109169' 
GROUP BY 
    C.nome
ORDER BY 
    C.nome;