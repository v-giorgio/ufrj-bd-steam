import pandas as pd
import requests

GAME_ACHIEVEMENTS = "http://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0002/?gameid="
USER_ACHIEVEMENTS = "http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?appid=APP_ID&key=66731D652FC2572E8D25BAE91032111A&steamid="

def get_game_achievements(app_ids: list) -> pd.DataFrame:

    df_games_achievements = pd.DataFrame()
    
    for app_id in app_ids:
        response = requests.get(f"{GAME_ACHIEVEMENTS}{app_id}")
        game_achievements_data = response.json()
        
        if response.status_code == 200 and len(game_achievements_data['achievementpercentages']['achievements']) > 0:
            game_achievements_data = game_achievements_data['achievementpercentages']['achievements']

            for achiev in game_achievements_data:
                df_achievement = pd.DataFrame([{
                    "appid": app_id,
                    "id": achiev.get('name', ""),
                    "percentual_obtencao_usuarios": achiev.get('percent', 0.0)
                }]).fillna("")

                df_games_achievements = pd.concat([df_games_achievements, df_achievement], ignore_index=True)
    
    df_games_achievements = df_games_achievements[df_games_achievements['appid'].isin(app_ids)]
            
    return df_games_achievements

def get_achievements_by_user(user_ids: list, achievements_app_ids: list) -> pd.DataFrame:
    df_users_achievements = pd.DataFrame()
    
    for app_id in achievements_app_ids:
        for steam_id in user_ids:
            response = requests.get(f"{USER_ACHIEVEMENTS}{steam_id}".replace("APP_ID", str(app_id)))
            
            user_achievements_data = response.json()
             
            if response.status_code == 200 and len(user_achievements_data['playerstats']['achievements']) > 0 and user_achievements_data['playerstats']['success']:
                user_achievements_data = user_achievements_data['playerstats']['achievements']

                for achiev in user_achievements_data:
                    if (achiev['achieved'] == 1):
                        df_achievement = pd.DataFrame([{
                            "usuario_id": steam_id,
                            "conquista_id": achiev['apiname']
                        }]).fillna("")

                        df_users_achievements = pd.concat([df_users_achievements, df_achievement], ignore_index=True)

    df_users_achievements.drop_duplicates(subset=['usuario_id', 'conquista_id'], inplace=True)

    return df_users_achievements