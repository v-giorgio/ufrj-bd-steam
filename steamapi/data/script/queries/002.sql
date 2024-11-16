/*
2. trazer a relação de conquistas obtidas e conquistas não obtidas de um usuário para um dado jogo
> será útil para tela de exibição das conquistas de usuário para um jogo
(i) junção externa (left join)
*/
SELECT 
    C.id as nome_conquista,
    CASE
        WHEN UC.fk_Conquista_id IS NULL THEN 'false'
        ELSE 'true'
    END AS possui_conquista
FROM JogoUsuario J
JOIN Conquista C
    ON C.fk_Jogo_id = J.fk_Jogo_id
LEFT JOIN UsuarioConquista UC
    ON UC.fk_Conquista_id = C.id
WHERE J.fk_Usuario_id = '76561198128399931' 
  AND J.fk_Jogo_id = '20920';