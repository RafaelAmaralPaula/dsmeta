import logo from '../../assets/img/logo.svg'

import './styles.css'

function Header(){
    return(
        <header>
            <div className="dsmeta-logo-container">
                <img src={logo} alt="DSMeta" />
                <h1>DSMeta</h1>
                <p>
                Developed by
                <a href="https://www.instagram.com/rafael_amaral_paula" target="_blank"> @rafael_amaral_paula</a>
                </p>
            </div>
        </header>   
    )
}

export default Header