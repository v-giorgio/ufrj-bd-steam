import React from 'react';
import { Outlet } from 'react-router-dom'; // To render the current route's component

import Header from './header/Header'
import Footer from './footer/Footer'

function Layout() {
    return (
        <div>
            <Header />
            <main>
                <Outlet /> {/* É aqui que os componentes roteados vão ser exibidos */}
            </main>
            <Footer />
        </div>
    );
}

export default Layout;