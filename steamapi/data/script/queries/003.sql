/*
3. mostrar somente um vídeo (trailer) do jogo se houver; se não, mostrar 3 imagens, se houver
> será útil para quando o usuário fizer um hover na capa do jogo, mostrar detalhes do jogo (mídia)
(i) mais de uma junção
(ii) junção externa (left join)
*/
SELECT 
    J.nome AS nome_jogo,
    V.url AS midia_url,
    (1) AS eh_video
FROM Jogo J
LEFT JOIN (
    SELECT fk_Jogo_id, MIN(url) AS url
    FROM Video
    GROUP BY fk_Jogo_id
) V ON J.id = V.fk_Jogo_id
WHERE V.url IS NOT NULL

UNION

SELECT 
    J.nome AS nome_jogo,
    I.url AS midia_url,
    (0) AS eh_video
FROM Jogo J
JOIN (
    SELECT fk_Jogo_id, url
    FROM (
        SELECT 
            fk_Jogo_id, 
            url,
            ROW_NUMBER() OVER (PARTITION BY fk_Jogo_id ORDER BY url DESC) AS rn
        FROM Imagem
        WHERE capa = 'FALSE'
    ) I_filtered
    WHERE rn <= 3
) I ON J.id = I.fk_Jogo_id
WHERE NOT EXISTS (
    SELECT 1
    FROM Video V
    WHERE V.fk_Jogo_id = J.id
);