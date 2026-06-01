# 外部設計書

## 1. ユーザーインターフェース
- HTML5 / CSS3 (Bootstrap 5 を想定)
- JavaScript (Flatpickr などのカレンダーライブラリを使用)
- Thymeleaf (テンプレートエンジン)

## 2. 外部API連携
- **API**: openbd (https://openbd.jp/)
- **エンドポイント**: `https://api.openbd.jp/v1/get?isbn=`
- **取得データ**: タイトル(title), 著者(author), 出版社(publisher), 発売日(pubdate), 画像(cover)

## 3. バリデーションルール
- ISBN: 10桁または13桁の数字。
- 読書開始日: 必須ではないが、読書終了日より前の日付であること。
- 読書終了日: 任意。

## 4. エラーハンドリング
- APIからデータが取得できない場合、「本が見つかりませんでした」と表示する。
- DB保存失敗時は共通エラー画面を表示する。
