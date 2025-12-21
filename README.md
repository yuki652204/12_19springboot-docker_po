# springboot-dev-docker-template
以下の記事のサンプルです。  
https://qiita.com/devnokiyo/items/214aa24d60764f0f55f6
# Spring Boot お問い合わせ管理アプリ

Spring Boot + Docker + MySQL を使用した  
お問い合わせフォームおよび管理画面を備えた Web アプリケーションです。

MVCアーキテクチャを意識し、  
CRUD（作成・一覧・編集・削除）機能を実装しています。

---

## 概要

本アプリは、ユーザーからのお問い合わせを登録し、  
管理者が一覧・編集・削除できるシンプルな管理アプリです。

Spring Boot を用いた MVC 構成、  
Spring Data JPA による DB 操作、  
Docker による開発環境構築を目的として作成しました。

---

## 使用技術

- Java 17
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL 8.0
- Docker / Docker Compose
- Git / GitHub（SSH 接続）

---

## 機能一覧

### ユーザー機能
- お問い合わせ投稿

### 管理機能
- お問い合わせ一覧表示
- お問い合わせ編集
- お問い合わせ削除

（CRUD 操作を一通り実装）

---

## 画面一覧

- トップページ
- お問い合わせフォーム
- 管理画面（お問い合わせ一覧）
- 管理画面（お問い合わせ編集）

---

## データベース構成

### inquiry テーブル

| カラム名 | 型 | 説明 |
|--------|----|----|
| id | BIGINT | 主キー |
| name | VARCHAR | 名前 |
| mail | VARCHAR | メールアドレス |
| content | TEXT | お問い合わせ内容 |

---

## MVC構成

- **Model**
  - Entity：Inquiry
  - Repository：InquiryRepository
- **View**
  - Thymeleaf テンプレート
- **Controller**
  - 画面遷移・入力処理・バリデーション制御

---

## 起動方法（Docker）

```bash
git clone git@github.com:yuki652204/12_19springboot-docker_po.git
cd springboot-dev-docker-template-master
docker compose up -d
