const actions = {
    search: ({ commit }, value) => {
        setTimeout(() => {
            commit('search', value)
        }, 100)
    },
    selectSession: ({ commit }, value) => commit('selectSession', value),
    selectFriend: ({ commit }, value) => commit('selectFriend', value),
    sendMessage: ({ commit }, msg) => commit('sendMessage', msg),
    send: ({ commit },value) => commit('send',value),
    initData: ({ commit }) => commit('initData'),
}

export default actions;