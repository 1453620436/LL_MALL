<template>
  <div>
    <div class="my-container">
      <div class="my-title">
        <h1>用户资料</h1>
        <h3>（亲爱的 <span style="color: red;">{{userInfo.username}}</span>，您可以填写更完整的资料）</h3>
      </div>
      <div class="my-box">
        <Form ref="formData" :model="formData" label-position="left" :label-width="100" :rules="ruleInline">
          <FormItem label="当前头像：" prop="icon">
            <div class="item_bock head_p">
              <div class="head_img">
                <img :src="formData.icon" />
              </div>
              <div class="setting_right" @click.stop="uploadHeadImg">
                <div class="caption">更改头像</div>
              </div>
              <input type="file" accept="image/*" @change="handleFile" class="hiddenInput" />
            </div>
          </FormItem>
          <FormItem label="用户名：" prop="username">
            <i-input v-model="formData.username" size="large"></i-input>
          </FormItem>
          <FormItem label="真实姓名：" prop="realname">
            <i-input v-model="formData.realname" size="large"></i-input>
          </FormItem>
          <FormItem label="性别：" prop="sex">
            <input type="radio" id="woman" name="gender" value="0" v-model="formData.sex">
            <label for="woman">女</label>
            <input type="radio" id="man" name="gender" value="1" v-model="formData.sex">
              <label for="man">男</label>
          </FormItem>
          <FormItem label="手机号码：" prop="telethone">
            <i-input v-model="formData.telethone" size="large" disabled="disabled"></i-input>
          </FormItem>
          <FormItem label="注册邮箱：" prop="email">
            <i-input v-model="formData.email" size="large" disabled="disabled"></i-input>
          </FormItem>
        </Form>
      </div>
      <div class="update-submit">
        <!--图标加文字按钮-->
        <Button type="primary" long @click="updateInfo()">保存</Button>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    mapState
  } from 'vuex';
  import Distpicker from 'v-distpicker';
  export default {
    addressee: 'MyInfo',
    data() {
      return {
        formData: {
          icon: 'static/img/head.png',
          username: '',
          realname: '',
          sex: '',
          telethone: '',
          email: ''
        },
        ruleInline: {
          username: [{
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }],
          email: [{
            required: true,
            message: '请输入邮箱',
            trigger: 'blur'
          }, {
            type: 'email',
            message: '邮箱格式错误',
            trigger: 'blur'
          }],
          telethone: [{
              required: true,
              message: '手机号不能为空',
              trigger: 'blur'
            },
            {
              type: 'string',
              pattern: /^1[3|4|5|7|8][0-9]{9}$/,
              message: '手机号格式出错',
              trigger: 'blur'
            }
          ]
        }
      };
    },
    computed: {
      ...mapState(['userInfo'])
    },
    methods: {
      getProvince(data) {
        this.formData.province = data.value;
      },
      getCity(data) {
        this.formData.city = data.value;
      },
      getArea(data) {
        this.formData.area = data.value;
      },
      // 打开图片上传
      uploadHeadImg: function() {
        this.$el.querySelector('.hiddenInput').click(); //获取文档中 class=”hiddenInput” 的元素
      },
      // 将头像显示
      handleFile: function(e) { //在打开文件夹选中图片确认后，执行handleFile函数
        let $target = e.target || e.srcElement //let $target = e.target || e.srcElement 表示调用他的各种属性，两个的区别是：ie下支持e.srcElement，ff支持e.target。
        let file = $target.files[0]; //表示取第一张图
        var reader = new FileReader(); // 对象允许Web应用程序异步读取存储在用户计算机上的文件（或原始数据缓冲区）的内容，使用 File 或 Blob 对象指定要读取的文件或数据
        reader.onload = (data) => { //onload 事件会在页面或图像加载完成后立即发生/
          let res = data.target || data.srcElement
          this.formData.icon = res.result;
        }
        reader.readAsDataURL(file); //FileReader对象的readAsDataURL方法可以将读取到的文件编码成Data URL
      }
    },
    updateInfo() {

    },
    components: {
      Distpicker
    }
  };
</script>

<style scoped>
  .my-container {
    margin: 15px auto;
    width: 60%;
    min-width: 600px;
  }

  .my-title {
    margin-bottom: 15px;
    text-align: center;
  }

  .update-submit {
    display: flex;
    justify-content: center;
  }

  .item_bock {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 60px;
    width: 500px;
    padding: 0px 24px 0px 38px;
    border-bottom: 1px solid #f7f7f7;
    background: #fff;
  }

  .head_p {
    height: 132px;
  }

  .head_p {
    height: 90px;
  }

  .head_img {
    height: 90px;
  }

  .head_img img {
    width: 90px;
    height: 90px;
    border-radius: 50px
  }

  .setting_right {
    display: flex;
    height: 37px;
    justify-content: flex-end;
    align-items: center;
  }

  .hiddenInput {
    display: none;
  }

  .caption {
    color: #8F8F8F;
    font-size: 20px;
    height: 37px;
    cursor: pointer;
  }
</style>
