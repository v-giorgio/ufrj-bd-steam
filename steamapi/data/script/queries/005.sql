/*
5. quantidade de jogos que usuario possui por categoria
- (i) mais de uma junção
- (ii) agregação com agrupamento
*/
SELECT 
    U.apelido AS usuario,
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
    U.id = '76561198128399931' 
GROUP BY 
    C.nome
ORDER BY 
    C.nome;