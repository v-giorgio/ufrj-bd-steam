
import pandas as pd
import requests

USER_DETAILS_URL = f"https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=66731D652FC2572E8D25BAE91032111A&steamids="
FRIENDS_LIST_URL = "https://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key=66731D652FC2572E8D25BAE91032111A&steamid=FRIENDID&relationship=friend"

def get_user_details(steamid: str):
    
    response = requests.get(f"{USER_DETAILS_URL}{steamid}")
    
    if response.status_code == 200:
        user_data = response.json()
        return pd.DataFrame(
            user_data['response']['players'],
            columns=['steamid', 'personaname', 'profileurl', 'avatar', 'realname', 'loccountrycode']
        ).fillna("")
    else:
        print(f"Erro ao obter detalhes do usuário: {response.status_code}")
        return pd.DataFrame()

def get_user_friends(steamid: str):
    response = requests.get(FRIENDS_LIST_URL.replace("FRIENDID", steamid))

    if response.status_code == 200:
        friends_data = response.json()
        
        friends_list = friends_data['friendslist']['friends'][:19]

        friends_details = []

        for friend in friends_list:
            df_friend = get_user_details(friend['steamid'])
            if not df_friend.empty:
                friends_details.append(df_friend)
        
        df_friends = pd.concat(friends_details, ignore_index=True)
        
        return df_friends
    else:
        print(f"Erro ao obter a lista de amigos: {response.status_code}")

def pre_process_users_df(df: pd.DataFrame):
    columns_mapping = {
        'steamid': 'id',
        'profileurl': 'url_perfil', 
        'personaname': 'username',
        'avatar': 'avatar', 
        'loccountrycode': 'pais_origem'
    }
    
    df = df.rename(columns=columns_mapping)

    df['nome'] = df.apply(
        lambda row: row['realname'] if pd.notna(row['realname']) and row['realname'] != "" else row['username'],
        axis=1
    )

    df.drop('realname', axis=1, inplace=True)

    df['apelido'] = df['url_perfil'].str.extract(r'/id/([^/]+)/?$')

    return df