import pandas as pd

DATA_PATH = "./generated"

from utils import (
    get_user_details, 
    get_user_friends, 
    pre_process_users_df, 
    get_user_games, 
    get_games_details, 
    generate_images_videos_categories_dataframes, 
    get_game_achievements,
    get_achievements_by_user
)

# 1) pegar dados do usuário default
USER_DEFAULT = "76561198098109169"
df_users = get_user_details(USER_DEFAULT)

# 2) pegar dados de 19 amigos do usuario e fazer pré-processamento
df_friends_temp = get_user_friends(USER_DEFAULT)
df_users = pre_process_users_df(pd.concat([df_users, df_friends_temp], ignore_index=True))

del df_friends_temp

# 3) pegar 20 appIds de jogos mais jogados por cada um dos 20 usuários registrados
df_users_games = get_user_games(df_users['id'].to_list())

# 4) pegar os detalhes dos jogos obtidos e reunir em um unico df (join com os appids de 3)) - somente trazer quando success === true
app_ids = df_users_games.drop_duplicates(subset='appid')['appid'].to_list()

df_games = get_games_details(app_ids)

# 5) Criar df para imagens, videos e categorias
df_images, df_videos, df_categories, df_categories_games = generate_images_videos_categories_dataframes(df_games)

# 8) buscar conquistas de um jogo e adicionar a um df
df_game_achievements = get_game_achievements(app_ids)

# 9) buscar conquistas de um usuario e adicionar a um df (somente quando achieved === 1)
achievements_app_ids = df_game_achievements.drop_duplicates(subset='appid')['appid'].to_list()
user_ids = df_users['id'].to_list()
df_user_achievements = get_achievements_by_user(user_ids, achievements_app_ids)

df_users.to_csv(f'{DATA_PATH}/users.csv', index=False)
df_users_games.to_csv(f'{DATA_PATH}/users_games.csv', index=False)
df_games.to_csv(f'{DATA_PATH}/games.csv', index=False)
df_images.to_csv(f'{DATA_PATH}/images.csv', index=False)
df_videos.to_csv(f'{DATA_PATH}/videos.csv', index=False)
df_categories.to_csv(f'{DATA_PATH}/categories.csv', index=False)
df_categories_games.to_csv(f'{DATA_PATH}/categories_games.csv', index=False)
df_game_achievements.to_csv(f'{DATA_PATH}/achievements.csv', index=False)
df_user_achievements.to_csv(f'{DATA_PATH}/users_achievements.csv', index=False)

del achievements_app_ids, user_ids