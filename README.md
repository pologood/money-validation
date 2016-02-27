# Money Validation

[![Money magnifier](docs/money-validation.jpg)](https://pixabay.com/en/coins-handful-russia-ruble-kopek-650779/)

[![Build Status](https://img.shields.io/travis/zalando/money-validation.svg)](https://travis-ci.org/zalando/money-validation)
[![Coverage Status](https://img.shields.io/coveralls/zalando/money-validation.svg)](https://coveralls.io/r/zalando/money-validation)
[![Release](https://img.shields.io/github/release/zalando/money-validation.svg)](https://github.com/zalando/money-validation/releases)
[![Maven Central](https://img.shields.io/maven-central/v/org.zalando/money-validation.svg)](https://maven-badges.herokuapp.com/maven-central/org.zalando/money-validation)

Validation support for `javax.money.MonetaryAmount` using the `javax.validation` constraints `DecimalMin` and `DecimalMax`.

## Usage

Use `DecimalMin` and `DecimalMax` on your fields of type `javax.money.MonetaryAmount`. The validators are automatically 
registered by your validation framework using SPI.

```java
class Model {

    @DecimalMin("0")
    public MonetaryAmount amount1;
    
    @DecimalMax(value = "0", inclusive = false)
    public MonetaryAmount amount2;
        
}
```

## License

Copyright [2015] Zalando SE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
