# テスト報告書

## 1. 実施内容
- 単体テスト (JUnit + Mockito): UseCase層のロジック検証。
- 結合テスト (Spring Boot Test + MockMvc): 画面遷移およびDB保存の検証。
- システムテスト (手動想定のシナリオを自動テストでカバー): ISBN登録から一覧表示、更新までの一連の流れ。

## 2. テスト結果
### 単体テスト
- `BookUseCaseTest`: 2件実施、2件成功。
    - `testFindExternalBook`: openbd APIクライアントとの連携が正しく行われること。
    - `testRegisterBook`: リポジトリへの保存が正しく呼び出されること。

### 結合・システムテスト
- `IntegrationTest`: 2件実施、2件成功。
    - `testRegisterAndList`: 登録処理後に一覧画面へ遷移し、登録内容が表示されること。
    - `testUpdateEndDate`: 登録済みの本の読了日が更新できること。

## 3. 結論
全てのテスト項目において期待通りの動作を確認しました。
Thymeleafのインラインスクリプトに関する制約についても、データ属性を利用した実装に改善済みです。
