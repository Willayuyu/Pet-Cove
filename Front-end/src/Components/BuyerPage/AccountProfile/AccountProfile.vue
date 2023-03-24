<template>
    
    <div >
        
        <b-tab title-item-class="mt-1" title="My Account" active>
                <b-card header="My Account">
                    <ProfileForm/>
                </b-card>
            
        </b-tab>
    </div>

</template>

<script>
// import UserProfile from './UserProfile.vue';

// import TestPage from '../../../views/Test/TestPage.vue';
import ProfileForm from './ProfileForm.vue';

export default{
    components: {
        // UserProfile,
        // TestPage,
        ProfileForm
    }
}


</script>