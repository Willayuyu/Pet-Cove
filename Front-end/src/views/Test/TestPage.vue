<!-- <template>
  <div class="list">
    <div class="list-item" v-for="(item, index) in list" :key="index">
      <div class="item-header">
        {{ item.title }}
        <button @click="toggleExpand(index)">
          {{ item.isExpanded ? '收起' : '展开' }}
        </button>
      </div>
      <transition name="expand">
        <div class="item-content" v-show="item.isExpanded">
          {{ item.description }}
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      list: [
        { title: '01', description: '23$, Completed', isExpanded: false },
        { title: '02', description: '53$, Started', isExpanded: false },
        { title: '03', description: '56$, Delivery', isExpanded: false }
      ]
    }
  },
  methods: {
    toggleExpand(index) {
      this.list[index].isExpanded = !this.list[index].isExpanded;
    }
  }
}
</script> -->

<!-- 

<template>
  <div>
    <b-table :items="items" :fields="fields" :per-page="perPage" :current-page="currentPage">
      <template #cell(expand)="row">
        <button @click="toggleExpand(row.item.id)">
          {{ row.item.isExpanded ? '收起' : '展开' }}
        </button>
      </template>
      <template #cell(expanded)="row">
        <transition name="expand">
          <div class="item-content" v-show="row.item.isExpanded">
            {{ row.item.description }}
          </div>
        </transition>
      </template>
    </b-table>
    <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" />
  </div>
</template>

<script>
export default {
  components: {
  },
  data() {
    return {
      items: [
        { id: '01', price: 23, status: 'Completed', description: 'Some description', isExpanded: false },
        { id: '02', price: 53, status: 'Started', description: 'Some description', isExpanded: false },
        { id: '03', price: 56, status: 'Delivery', description: 'Some description', isExpanded: false },
        // ... more items
      ],
      fields: [
        { key: 'id', label: 'ID' },
        { key: 'price', label: 'Price' },
        { key: 'status', label: 'Status' },
        { key: 'expand', label: '', sortable: false },
        { key: 'expanded', label: '', sortable: false }
      ],
      perPage: 10,
      currentPage: 1
    }
  },
  computed: {
    totalRows() {
      return this.items.length
    }
  },
  methods: {
    toggleExpand(id) {
      this.items = this.items.map(item => {
        if (item.id === id) {
          item.isExpanded = !item.isExpanded
        } else {
          item.isExpanded = false
        }
        return item
      })
    }
  }
}

</script>

<style>
.expand-enter-active,
.expand-leave-active {
  transition: all 0.5s;
}

.expand-enter,
.expand-leave-to {
  opacity: 0;
  height: 0;
}
</style> -->

<template>
  <div class="item" :class="{ 'expanded': isExpanded }">
    <div class="header" @click="toggleExpand">
      {{ item.title }}
      <i :class="[ 'fa', 'fa-chevron-' + (isExpanded ? 'up' : 'down') ]"></i>
    </div>
    <div class="content" :style="{ 'max-height': isExpanded ? contentHeight + 'px' : '0' }">
      {{ item.content }}
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isExpanded: false,
      contentHeight: 0
    };
  },
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  mounted() {
    // 在组件挂载后，获取展开内容的实际高度，并保存到 contentHeight 变量中
    this.contentHeight = this.$refs.content.offsetHeight;
  },
  methods: {
    toggleExpand() {
      this.isExpanded = !this.isExpanded;
    }
  }
};
</script>

<style scoped>
.item {
  margin-bottom: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #eee;
  cursor: pointer;
}

.content {
  padding: 10px;
  overflow: hidden;
  transition: max-height 0.3s ease-out; /* 添加动画效果 */
}

.expanded .content {
  max-height: 1000px; /* 将 max-height 设置为容器实际高度，以实现内容显示 */
}
</style>

