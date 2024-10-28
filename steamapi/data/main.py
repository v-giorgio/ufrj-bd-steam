import pandas as pd

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

DATA_PATH = "./generated"

USER_DEFAULT = "76561198098109169"
df_users = get_user_details(USER_DEFAULT)

df_friends_temp = get_user_friends(USER_DEFAULT)
df_users = pre_process_users_df(pd.concat([df_users, df_friends_temp], ignore_index=True))
del df_friends_temp

df_users_games = get_user_games(df_users['id'].to_list())
app_ids = df_users_games.drop_duplicates(subset='appid')['appid'].to_list()

df_games = get_games_details(app_ids)
df_games = df_games.drop_duplicates(subset='id')
app_ids = df_games['id'].to_list()

df_users_games = df_users_games[df_users_games['appid'].isin(app_ids)]

df_images, df_videos, df_categories, df_categories_games = generate_images_videos_categories_dataframes(df_games)

df_game_achievements = get_game_achievements(app_ids)

achievements_app_ids = df_game_achievements.drop_duplicates(subset='appid')['appid'].to_list()
user_ids = df_users['id'].to_list()
df_user_achievements = get_achievements_by_user(user_ids, achievements_app_ids)

del achievements_app_ids, user_ids

df_users.to_csv(f'{DATA_PATH}/users.csv', index=False)
df_users_games.to_csv(f'{DATA_PATH}/users_games.csv', index=False)
df_games.to_csv(f'{DATA_PATH}/games.csv', index=False)
df_images.to_csv(f'{DATA_PATH}/images.csv', index=False)
df_videos.to_csv(f'{DATA_PATH}/videos.csv', index=False)
df_categories.to_csv(f'{DATA_PATH}/categories.csv', index=False)
df_categories_games.to_csv(f'{DATA_PATH}/categories_games.csv', index=False)
df_game_achievements.to_csv(f'{DATA_PATH}/achievements.csv', index=False)
df_user_achievements.to_csv(f'{DATA_PATH}/users_achievements.csv', index=False)