<template>
    
    <div >
        
        <b-tab :active="activeTabIndex === 0" title-item-class="mt-1" title="My Account" active>
                <b-card header="My Account">
                    <ProfileForm/>
                </b-card>
            
        </b-tab>
    </div>

</template>

<script>
import ProfileForm from './ProfileForm.vue';

export default{
    components: {
        // UserProfile,
        // TestPage,
        ProfileForm
    }
}


</script>