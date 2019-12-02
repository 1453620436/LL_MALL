<template>
  <div class="info-form">
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="90">
      <FormItem label="身份证件号" prop="idCard">
        <i-input v-model="formValidate.idCard" clearable size="large" placeholder="请输入你的证件号"></i-input>
      </FormItem>
      <FormItem label="用户名" prop="username">
        <i-input v-model="formValidate.username" clearable size="large" placeholder="请输入你的姓名"></i-input>
      </FormItem>
      <FormItem label="邮箱" prop="email">
        <i-input v-model="formValidate.email" clearable size="large" placeholder="请输入你的邮箱"></i-input>
      </FormItem>
      <FormItem label="密码" prop="password">
        <i-input type="password" v-model="formValidate.password" clearable size="large" placeholder="请输入你的密码"></i-input>
      </FormItem>
      <FormItem label="确认密码" prop="repassword">
        <i-input type="password" v-model="formValidate.repassword" clearable size="large" placeholder="请再次输入你的密码"></i-input>
      </FormItem>
      <Button type="error" size="large" long @click="handleSubmit()">注册</Button>
    </Form>
  </div>
</template>

<script>
   import {
     mapMutations,
     mapActions
   } from 'vuex';
  export default {
    name: 'InputInfo',
    data() {
      const validatePassCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.formValidate.password) {
          callback(new Error('两次输入的密码不一样'));
        } else {
          callback();
        }
      };
      return {
        formValidate: {
          idCard: '',
          username: '',
          email: '',
          password: '',
          repassword: '',
          telethone:''
        },
        ruleValidate: {
          idCard: [{
            required: true,
            message: '证件号不能为空',
            trigger: 'blur',
          }, {
            type: 'string',
            pattern: /\d{17}[\d|x]|\d{15}/,
            message: '证件号格式出错',
            min: 18,
            max: 18,
            trigger: 'blur'
          }],
          username: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          }, {
            type: 'string',
            min: 4,
            message: '用户名长度有误',
            trigger: 'blur'
          }],
          email: [{
              required: true,
              message: '邮箱不能为空',
              trigger: 'blur'
            },
            {
              type: 'email',
              message: '邮箱格式错误',
              trigger: 'blur'
            }
          ],
          password: [{
              required: true,
              message: '密码不能为空',
              trigger: 'blur'
            },
            {
              type: 'string',
              min: 6,
              message: '密码长度不能小于6',
              trigger: 'blur'
            }
          ],
          repassword: [{
            validator: validatePassCheck,
            trigger: 'blur'
          }]
        }
      };
    },
    methods: {
      ...mapMutations(['SET_SIGN_UP_SETP']) ,
      handleSubmit() {
        const father = this;
        this.$refs.formValidate.validate((valid) => {
          if (valid) {
            this.formValidate.telethone = this.$route.query.telethone;
            this.$store.dispatch('Register', this.formValidate).then(() => {
              this.$Message.success('注册成功');
              father.SET_SIGN_UP_SETP(2);
              this.$router.push({
                path: '/SignUp/signUpDone'
              });
            }).catch(() => {
              console.log("注册失败");
            });
          } else {
            console.log("参数验证不合法");
            return false;
          }
        });
      }
    }
  };
</script>

<style scoped>
  .info-form {
    width: 90% !important;
  }
</style>
