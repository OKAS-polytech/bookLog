# ユースケース図

```mermaid
usecaseDiagram
    actor "ユーザー" as User
    package "本棚アプリ" {
        usecase "本を検索する" as UC1
        usecase "本を登録する" as UC2
        usecase "本の一覧を見る" as UC3
        usecase "読了日を更新する" as UC4
    }
    User --> UC1
    User --> UC2
    User --> UC3
    User --> UC4
```
