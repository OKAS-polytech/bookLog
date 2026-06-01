# 内部設計書

## 1. パッケージ構成 (Clean Architecture)
- `com.example.bookshelf.domain`: エンティティ、リポジトリ・インターフェース
- `com.example.bookshelf.usecase`: ユースケース（インターフェース、実装）
- `com.example.bookshelf.infrastructure`: リポジトリ実装、外部APIクライアント、DBエンティティ
- `com.example.bookshelf.presentation`: コントローラ、DTO、Form

## 2. 依存関係
- Presentation -> UseCase
- UseCase -> Domain
- Infrastructure -> Domain / UseCase

## 3. 使用技術
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (In-Memory for simplicity, can be changed to MySQL/PostgreSQL)
- RestTemplate (for openbd API)
- Thymeleaf

## 4. バリデーション実装
- Spring Validation (`@NotBlank`, `@Pattern` など) を Form クラスに使用する。
