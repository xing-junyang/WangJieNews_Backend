# Harmony_Homework_Backend

通过 `selenium` 和 `jsoup` 来获取网易网的数据、保存到数据库，并作为后端服务器为鸿蒙 APP 前端提供新闻服务。数据仅会在启动时更新。

直接运行目录下编译过的 `jar` 包，需要将适应系统版本的 `chromedriver` 放在相同目录下：

```shell

java -jar ./HarmonyOS_Homework_Backend-0.0.1-SNAPSHOT.jar

```

也可以通过 Maven 下载依赖并启动 Spring Boot. 需要将 `./chromedriver` 更换为适应系统版本的版本。