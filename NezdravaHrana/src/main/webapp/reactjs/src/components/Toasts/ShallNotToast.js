import React, {Component} from "react";
import {Image, Toast} from "react-bootstrap";
import youShallNotPass from "../../pictures/youShallNotPass.jpg";

export default class ShallNotToast extends Component{

    render() {
        const toastCss={
            position:'fixed',
            top: '80px',
            right: '70px',
            zIndex:'1',
            boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)'

        }

        return(
            <div style={this.props.children.shallShow ? toastCss : null}>
                <Toast className={"border border-danger bg-danger text-white"} shallShow={this.props.children.shallShow}>
                    <Toast.Header className={"bg-danger text-white"} closeButton={false}>
                        <strong className={"mr-auto"}>Неуспех</strong>
                    </Toast.Header>

                    <Toast.Body>
                        {this.props.children.message}
                        <Image className={"greatSuccess"} src={youShallNotPass} thumbnail />
                    </Toast.Body>
                </Toast>
            </div>
        )
    }
}