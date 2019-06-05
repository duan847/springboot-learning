## validator
### 简介
数据校验是任何一个应用程序都会用到的功能,无论是显示层还是持久层. 通常,相同的校验逻辑会分散在各个层中, 这样,不仅浪费了时间还会导致错误的发生(译注: 重复代码). 为了避免重复, 开发人员经常会把这些校验逻辑直接写在领域模型里面, 但是这样又把领域模型代码和校验代码混杂在了一起, 而这些校验逻辑更应该是描述领域模型的元数据.
validator使用人数较多的规则在javax的和Hibernate Validator中
### 引入
springboot-web默认会引入Hibernate Validator，所以，可以直接使用
### 使用

#### spring中使用

#### 已有的validator注解及作用
| 注解   |      支持的数据类型      | 作用 |
|----------|-------------|-------------|
| @Null | Object | 检查对象是否是Null |
| @NotNull | Object | 检查对象是否不是Null |
| @AssertTrue | Boolean | 检查元素值是否是true |
| @AssertFalse | Boolean | 检查元素值是否是false |
| @Min | String | 检查元素必须是数字，并且大于等于指定的值 |
| @Max | String | 检查元素必须是数字，并且小于等于指定的值 |
| @DecimalMin | BigDecimal | 检查元素必须是数字，并且大于等于指定的值 |
| @DecimalMax | BigDecimal | 检查元素必须是数字，并且小于等于指定的值 |
| @Size(min=, max=) | Array,Collection,Map,String | 检查元素的大小在（包括）min和max之间 |
| @Digits(integer, fraction) | Number,String | 检查元素必须是数字，并且在接受的范围内 |
| @Past | Date,Calendar | 检查元素必须是过去的日期 |
| @Future | Date,Calendar | 检查元素必须是将来的日期 |
| @Pattern(value) | String | 检查元素必须符合指定的正则表达式 |
|----以下是Hibernate|----|
| @CreditCardNumber | String | 检查字符串是否通过了Luhn（是一个算法）校验和测试 |
| @Email | String | 检查字符串是否是有效的电子邮件地址 |
| @Length(min=, max=) | String | 检查字符串长度在（包括）min和max之间 |
| @NotBlank | String | 检查字符串是否为空并且去两端空格（trim()）后长度大于0。与@NotEmpty的区别在于此约束只能应用于字符串并且忽略尾随空格。 |
| @NotEmpty | String, Collection, Map and arrays | 检查元素是否不是空的 |
| @Range(min=, max=) | BigDecimal, BigInteger, String, byte, short, int, long and the respective wrappers of the primitive types | 检查值在（包括）min和max之间 |
| @SafeHtml(whitelistType=, additionalTags=) | CharSequence | 检查带注释的值是否包含潜在的恶意片段，例如<script/>。为了使用此约束，jsoup库必须是类路径的一部分。使用whitelistType属性预定义白名单类型可以选择。您还可以使用该additionalTags属性为白名单指定其他html标记。 |
| @ScriptAssert(lang=, script=, alias=) | Any type | 要使用这个约束条件，必须先要保证Java Scripting API即JSR 223（“Java TM平台的脚本”）的实现在类路径当中。如果使用的时Java 6的话，则不是问题，如果是老版本的话，那么需要把JSR 223的实现添加进类路径。这个约束条件中的表达式可以使用任何兼容JSR 223的脚本来编写。（更多信息请参考的Javadoc） |
| @URL(protocol=, host=, port=, regexp=, flags=) | String | 根据RFC2396检查带注释的字符串是否为有效URL。如果任何可选参数protocol，host或port指定时，对应的URL片段必须在指定的值相匹配。可选参数regexp并flags允许指定URL必须匹配的附加正则表达式（包括正则表达式标志） |

#### 自定义validator注解

#### validator分组


参考：http://docs.jboss.org/hibernate/validator/4.2/reference/zh-CN/html_single/#validator-customconstraints
