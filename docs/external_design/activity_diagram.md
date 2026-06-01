# アクティビティ図

```mermaid
activityDiagram
    start
    :ISBNを入力;
    :確認ボタンをクリック;
    if (ISBNは有効?) then (yes)
        :openbd APIから情報取得;
        :本情報を表示;
        :読書日を入力;
        :登録ボタンをクリック;
        :DBに保存;
        :一覧画面へ遷移;
    else (no)
        :エラーメッセージ表示;
        :ISBN入力へ戻る;
    endif
    stop
```
