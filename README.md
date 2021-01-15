- [日本語](#概要)

## 概要
washiki-java は、超球・超球面すなわち高次元の球体・球面上に
一様に分布するランダムな点の座標を生成するライブラリです。

通常、このような点は [Box-Muller 法](
https://ja.wikipedia.org/wiki/%E3%83%9C%E3%83%83%E3%82%AF%E3%82%B9%EF%BC%9D%E3%83%9F%E3%83%A5%E3%83%A9%E3%83%BC%E6%B3%95
)を使って生成されますが、この方法では条件を満たす点が生成されるまで試行を繰り返すような無駄なステップが含まれています。
一方、washiki-java で採用している方法では、
[数学的基礎](#数学的基礎)の箇所で簡単に説明しているように、
比較的単純で効率的な方法でこのような点を生成します。
- [設定](#設定) (Maven)
- [Java コード](#Java-コード)
- [数学的基礎](#数学的基礎)

## 設定
washiki-java ライブラリは GitHub Packages に公開しているので、
Maven プロジェクトから使用するには少々設定が必要です。

#### ~/.m2/settings.xml
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_TOKEN</password>
    </server>
  </servers>
</settings>
```

#### pom.xml
```xml
<project xmlns="...">
  
  <dependencies>
    <dependency>
      <groupId>org.waman</groupId>
      <artifactId>washiki-java</artifactId>
      <version>WASHIKI_JAVA_VERSION</version>
    </dependency>
  </dependencies>

  <repositories>
    <repository>
      <id>github</id>
      <name>GitHub waman Apache Maven Packages</name>
      <url>https://maven.pkg.github.com/waman/washiki-java</url>
    </repository>
  </repositories>
</project>
```

## Java コード
```java
import org.waman.washiki.SphereRandom;
```

## 数学的基礎


```
  x₁ = r sinθ₁ sinφ₁
  x₂ = r sinθ₁ cosφ₁
  x₃ = r cosθ₁ sinφ₂
  x₄ = r cosθ₁ cosφ₂
```

```
  dV = r⁴sin²θ₁cosθ₁drdθ₁dφ₁dφ₂
     = (1/15)d(r⁵)d(sin³θ₁)dφ₁dφ₂
```